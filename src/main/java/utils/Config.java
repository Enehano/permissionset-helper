package utils;

public class Config {

    /**
     * Default values for specific parameters
     */
    public static final int DEFAULT_EXTRACT_REQUEST_SIZE = 500;
    public static final int DEFAULT_MIN_RETRY_SECS = 2;
    public static final int DEFAULT_MAX_RETRIES = 3;
    public static final int MAX_RETRIES_LIMIT = 10;
    public static final int DEFAULT_CONNECTION_TIMEOUT_SECS = 60;
    public static final int DEFAULT_TIMEOUT_SECS = 540;
    public static final int DEFAULT_LOAD_BATCH_SIZE = 200;
    public static final int DEFAULT_DAO_WRITE_BATCH_SIZE = 500;
    public static final int DEFAULT_DAO_READ_BATCH_SIZE = 200;
    public static final int MAX_LOAD_BATCH_SIZE = 200;
    public static final int MAX_DAO_READ_BATCH_SIZE = 200;
    public static final int MAX_DAO_WRITE_BATCH_SIZE = 2000;
    public static final int MAX_BULK_API_BATCH_BYTES = 10000000;
    public static final int MAX_BULK_API_BATCH_SIZE = 10000;
    public static final int DEFAULT_BULK_API_BATCH_SIZE = 2000;
    public static final long DEFAULT_BULK_API_CHECK_STATUS_INTERVAL = 5000L;
    public static final String DEFAULT_ENDPOINT_URL = "https://login.salesforce.com";
    public static final String OAUTH_PROD_ENVIRONMENT_VAL = "Production";
    public static final String OAUTH_SB_ENVIRONMENT_VAL = "Sandbox";

    public static final String OAUTH_PROD_SERVER_VAL = "https://login.salesforce.com/";
    public static final String OAUTH_SB_SERVER_VAL = "https://test.salesforce.com/";

    public static final String OAUTH_PROD_REDIRECTURI_VAL = "https://login.salesforce.com/services/oauth2/success";
    public static final String OAUTH_SB_REDIRECTURI_VAL = "https://test.salesforce.com/services/oauth2/success";
    public static final String OAUTH_PREFIX = "sfdc.oauth.";
    public static final String OAUTH_PARTIAL_BULK = "bulk";
    public static final String OAUTH_PARTIAL_PARTNER = "partner";
    public static final String OAUTH_PARTIAL_SERVER = "server";
    public static final String OAUTH_PARTIAL_CLIENTSECRET = "clientsecret";
    public static final String OAUTH_PARTIAL_CLIENTID = "clientid";
    public static final String OAUTH_PARTIAL_REDIRECTURI = "redirecturi";
    public static final String OAUTH_PARTIAL_BULK_CLIENTID = OAUTH_PARTIAL_BULK + "." + OAUTH_PARTIAL_CLIENTID;
    public static final String OAUTH_PARTIAL_PARTNER_CLIENTID = OAUTH_PARTIAL_PARTNER + "." + OAUTH_PARTIAL_CLIENTID;

    public static final String OAUTH_ENVIRONMENTS = OAUTH_PREFIX + "environments";
    public static final String OAUTH_ENVIRONMENT = OAUTH_PREFIX + "environment";
    public static final String OAUTH_ACCESSTOKEN = OAUTH_PREFIX + "accesstoken";
    public static final String OAUTH_REFRESHTOKEN = OAUTH_PREFIX + "refreshtoken";
    public static final String OAUTH_SERVER = OAUTH_PREFIX + OAUTH_PARTIAL_SERVER;
    public static final String OAUTH_CLIENTSECRET = OAUTH_PREFIX + OAUTH_PARTIAL_CLIENTSECRET;
    public static final String OAUTH_CLIENTID_PRESET = "3MVG9t0sl2P.pByp5SvgdeEOUdZVhSAs.9uYE.YSgPw5cRVpi7Yb78KopzGVN6jmuSzCiv6KpeXP25it7Jh2H";
    public static final String OAUTH_REDIRECTURI = OAUTH_PREFIX + OAUTH_PARTIAL_REDIRECTURI;
    public static final String OAUTH_LOGIN_FROM_BROWSER = OAUTH_PREFIX + "loginfrombrowser";
    public static final String REUSE_CLIENT_CONNECTION = "sfdc.reuseClientConnection";

    public static final String USERNAME = "sfdc.username"; //$NON-NLS-1$
    public static final String PASSWORD = "sfdc.password"; //$NON-NLS-1$
    public static final String ENDPOINT = "sfdc.endpoint"; //$NON-NLS-1$
    public static final String PROXY_HOST = "sfdc.proxyHost"; //$NON-NLS-1$
    public static final String PROXY_PORT = "sfdc.proxyPort"; //$NON-NLS-1$
    public static final String PROXY_USERNAME = "sfdc.proxyUsername"; //$NON-NLS-1$
    public static final String PROXY_PASSWORD = "sfdc.proxyPassword"; //$NON-NLS-1$
    public static final String PROXY_NTLM_DOMAIN = "sfdc.proxyNtlmDomain"; //$NON-NLS-1$

    public static final String RESOURCE_FOLDER = "src/main/resources/";
    // manifest file that controls which components get retrieved
    public static final String MANIFEST_FILE = RESOURCE_FOLDER + "package.xml";
    public static final String ZIP_FILE = "profiles.zip";
    public static final double API_VERSION = 29.0; // todo 54.0 ?
    // one second in milliseconds
    public static final long ONE_SECOND = 1000;
    // maximum number of attempts to deploy the zip file
    public static final int MAX_NUM_POLL_REQUESTS = 50;
    public static final String PROFILE_METADATA_SUFFIX = ".profile";
    public static final String PERMISSIONSET_METADATA_SUFFIX = ".permissionset";
}


