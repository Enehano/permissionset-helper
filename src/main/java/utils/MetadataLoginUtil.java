package utils;

import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.LoginResult;
import com.sforce.soap.metadata.MetadataConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

/**
 * Login utility.
 */
public class MetadataLoginUtil {

    public static MetadataConnection login() throws ConnectionException {
        final String USERNAME = "cauchemar00@resilient-fox-75w3nf.com";
        // This is only a sample. Hard coding passwords in source files is a bad practice.
        final String PASSWORD = "Ho6C3Y1AZN3AoyduAHuvSbkbTMw7wX7V8mVf";
        final String URL = "https://resilient-fox-75w3nf-dev-ed.my.salesforce.com/services/Soap/c/54.0";
        final LoginResult loginResult = loginToSalesforce(USERNAME, PASSWORD, URL);
        return createMetadataConnection(loginResult);
    }

    public static MetadataConnection oauthLogin(OAuthToken token) throws ConnectionException {
        return createMetadataConnection(token.getAccessToken(),  token.getInstanceUrl() + "/services/Soap/m/54.0/00D7Q000000HMn8");
    }

    private static MetadataConnection createMetadataConnection(
            final LoginResult loginResult) throws ConnectionException {
        final ConnectorConfig config = new ConnectorConfig();
        System.out.println(loginResult.getMetadataServerUrl());
        config.setServiceEndpoint(loginResult.getMetadataServerUrl());
        config.setSessionId(loginResult.getSessionId());
        return new MetadataConnection(config);
    }

    private static MetadataConnection createMetadataConnection(
            String token, String endpointUrl) throws ConnectionException {
        final ConnectorConfig config = new ConnectorConfig();
        config.setServiceEndpoint(endpointUrl);
        config.setSessionId(token);
        return new MetadataConnection(config);
    }

    private static LoginResult loginToSalesforce(
            final String username,
            final String password,
            final String loginUrl) throws ConnectionException {
        final ConnectorConfig config = new ConnectorConfig();
        config.setAuthEndpoint(loginUrl);
        config.setServiceEndpoint(loginUrl);
        config.setManualLogin(true);
        return (new EnterpriseConnection(config)).login(username, password);
    }
}