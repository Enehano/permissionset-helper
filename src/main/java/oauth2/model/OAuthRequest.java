package oauth2.model;


/**
 * Every OAuth2 request has a grant type and a client id (also known as consumer key).
 * 
 * @author gwester
 */
public abstract class OAuthRequest {
	
	//also known as 'consumer key' in Salesforce documentation
	private String client_id;		

	//NOTE: never distribute a client secret with client (mobile or desktop) software!
	private String client_secret;
	
	/**
	 * @return Also known as 'client id' in OAuth2
	 */
	public String getConsumerKey() {
		return client_id;
	}
	
	/**
	 * NOTE: never distribute a client secret with client (mobile or desktop) software!
	 * @return
	 */
	public String getConsumerSecret() {
		return client_secret;
	}
	
	/**
	 * NOTE: never distribute a client secret with client (mobile or desktop) software!
	 * @param client_secret also known as 'client secret'
	 */
	public void setConsumerSecret(String client_secret) {
		this.client_secret = client_secret;
	}
	
	/**
	 * @param consumerKey Also known as 'client id' in OAuth2
	 */
	protected void setConsumerKey(String client_id) {
		this.client_id = client_id;
	}
	
	/**
	 * All child classes must define a grant type, like:
	 * 
	 * authorization_code   //callbacks
	 * password             //username+password flow
	 * refresh_token        //trade in an expired session for another session
	 * client_credentials   //not supported at Salesforce
	 * 
	 * Please see the OAuth2 specification for complete details:
     * http://tools.ietf.org/html/draft-ietf-oauth-v2
	 * 
	 * @return
	 */
	public abstract String getGrantType();
}
