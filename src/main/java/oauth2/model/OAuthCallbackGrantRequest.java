package oauth2.model;


/**
 * This class that captures the fields needed for the callback OAuth flow.
 * 
 * See: http://wiki.developerforce.com/page/Digging_Deeper_into_OAuth_2.0_on_Force.com
 * 
 * @author gwester
 */
public class OAuthCallbackGrantRequest extends OAuthRequest {
	
	private final String code;
	private final String redirect_uri;
	
	private final String grant_type = "authorization_code";
	
	public OAuthCallbackGrantRequest(String client_id, String client_secret, String code, String redirect_uri) {
		super();
		super.setConsumerKey(client_id);
		super.setConsumerSecret(client_secret);
		this.code = code;
		this.redirect_uri = redirect_uri;
	}

	@Override
	public String getGrantType() {
		return grant_type;
	}

	/**
	 * @return The code you need to pass back to Salesforce to get an access token.
	 */
	public String getCode() {
		return code;
	}

	public String getRedirectUri() {
		return redirect_uri;
	}
}
