package controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import oauth2.client.SimplePost;
import oauth2.client.SimplePostFactory;
import oauth2.model.ParameterLoadException;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Config;
import utils.OAuthToken;

import java.io.*;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class OAuthController {

    private final Logger log = LogManager.getLogger(OAuthController.class.getSimpleName());
    private Thread checkLoginThread;
    enum LoginStatus { WAIT, FAIL, SUCCESS };
    protected static final Config config = new Config();
    private static LoginStatus loginResult = LoginStatus.WAIT;
    final static AtomicBoolean isDialogOpen = new AtomicBoolean(true);
    private static OAuthToken token;

    public OAuthController(String endpoint){
        setLoginStatus(LoginStatus.WAIT);

        String oAuthTokenURLStr = endpoint + "/services/oauth2/token";
        SimplePost client = SimplePostFactory.getInstance(config, oAuthTokenURLStr,
                new BasicNameValuePair("response_type", "device_code"),
                new BasicNameValuePair("client_id", System.getenv("CLIENT_ID")),
                new BasicNameValuePair("scope", "api")
        );
        try {
            client.post();
            InputStream in = client.getInput();
            if (!client.isSuccessful()) {
                ByteArrayOutputStream result = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                for (int length; (length = in.read(buffer)) != -1; ) {
                    result.write(buffer, 0, length);
                }
                String response = result.toString("UTF-8");
                System.out.println(response);

            }
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            final Map responseMap = mapper.readValue(in, Map.class);
            final String deviceCode = (String) responseMap.get("device_code");
            final String userCodeStr = (String) responseMap.get("user_code");
            log.debug("User Code: " + userCodeStr);
            final String verificationURLStr = (String) responseMap.get("verification_uri")
                    + "?user_code=" + userCodeStr;
            log.debug("Verification URL: " + verificationURLStr);

            openURL(verificationURLStr);

            int pollingIntervalInSec = 5;
            try {
                pollingIntervalInSec = ((Integer) responseMap.get("interval")).intValue();
            } catch (NumberFormatException e) {
                // fail silently
            }

            checkLoginThread = checkLoginStatus(pollingIntervalInSec, oAuthTokenURLStr, deviceCode);

        } catch (IOException | ParameterLoadException e) {
            log.error(e.getMessage());
        }
    }

    public boolean isLoginProcessCompleted() {
        return !this.checkLoginThread.isAlive();
    }

    private Thread checkLoginStatus(final int pollingIntervalInSec, final String oAuthTokenURLStr, final String deviceCode) {
        Thread successfulLogincheckerThread = new Thread() {
            public void run() {
                // Poll for 20 minutes.
                // Expiry of user code is detected by server returning an error
                // other than 'authorization_pending'.
                int maxPollingTimeInSec = 1200;
                int elapsedTimeInSec = 0;
                SimplePost client;
                InputStream in;
                while (elapsedTimeInSec <= maxPollingTimeInSec && isDialogOpen.get()) {
                    try {
                        Thread.sleep(pollingIntervalInSec * 1000);
                    } catch (InterruptedException e) {
                        // do nothing
                    }
                    elapsedTimeInSec += pollingIntervalInSec;
                    client = SimplePostFactory.getInstance(config, oAuthTokenURLStr,
                            new BasicNameValuePair("grant_type", "device"),
                            new BasicNameValuePair("client_id", System.getenv("CLIENT_ID")),
                            new BasicNameValuePair("code", deviceCode)
                    );
                    try {
                        client.post();
                    } catch (ParameterLoadException e) {
                        log.error(e.getMessage());
                        setLoginStatus(LoginStatus.FAIL);
                        return;
                    } catch (IOException e) {
                        log.error(e.getMessage());
                        setLoginStatus(LoginStatus.FAIL);
                        return;
                    }
                    in = client.getInput();
                    if (client.isSuccessful()) {
                        try {

                            StringBuilder builder = new StringBuilder();
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInput(), "UTF-8"));
                            for (int c = bufferedReader.read(); c != -1; c = bufferedReader.read()) {
                                builder.append((char) c);
                            }

                            String jsonTokenResult = builder.toString();
                            Gson gson = new GsonBuilder()
                                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                    .create();
                            token = gson.fromJson(jsonTokenResult, OAuthToken.class);
                            if(token!=null) {
                                //todo log
                            }
                        } catch (IOException e) {
                            log.error(e.getMessage());
                            setLoginStatus(LoginStatus.FAIL);
                            return;
                        }
                        // got the session id => SUCCESSful login
                        setLoginStatus(LoginStatus.SUCCESS);
                        return;
                    } else { // read the error message and log it
                        ObjectMapper mapper = new ObjectMapper();
                        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
                        try {
                            Map responseMap = mapper.readValue(in, Map.class);
                            String errorStr = (String)responseMap.get("error");
                            String errorDesc = (String)responseMap.get("error_description");
                            if ("authorization_pending".equalsIgnoreCase(errorStr)) {
                                // waiting for the user to login
                                log.debug(errorStr + " - " + errorDesc);
                            } else {
                                // a failure occurred. Exit.
                                log.error(errorStr + " - " + errorDesc);
                                setLoginStatus(LoginStatus.FAIL);
                                break;
                            }
                        } catch (IOException e) {
                            log.debug(e.getMessage());
                            continue;
                        }
                    }
                } // while loop
                log.error("User closed the dialog or timed out waiting for login");
                setLoginStatus(LoginStatus.FAIL);
            }
        };
        successfulLogincheckerThread.start();
        return successfulLogincheckerThread;
    }

    private synchronized void setLoginStatus(LoginStatus value) {
        loginResult = value;
    }

    public static OAuthToken getToken() {
        return token;
    }

    private void openURL(String url) {
        Runtime runtime = Runtime.getRuntime();
        String osName = System.getProperty("os.name");
        try {
            if (osName.toLowerCase().indexOf("mac") >= 0) {
                runtime.exec("open " + url);
            }
            else if (osName.toLowerCase().indexOf("win") >= 0) {
                runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else { //assume Unix or Linux
                String[] browsers = {
                        "firefox", "chrome", "opera", "konqueror", "epiphany", "mozilla", "netscape" };
                String browser = null;
                for (int count = 0; count < browsers.length && browser == null; count++)
                    if (runtime.exec(
                            new String[] {"which", browsers[count]}).waitFor() == 0) {
                        browser = browsers[count];
                    }
                if (browser == null) {
                    throw new Exception("Could not find web browser");
                } else {
                    runtime.exec(new String[] {browser, url});
                }
            }
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
