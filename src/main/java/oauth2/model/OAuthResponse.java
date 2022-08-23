package oauth2.model;

import java.net.MalformedURLException;
import java.util.Date;

/**
 * These are the fields Salesforce returns with an OAuth2 response.
 * 
 * Please see the OAuth2 specification for complete details:
 * http://tools.ietf.org/html/draft-ietf-oauth-v2
 * 
 * @author gwester
 */
public class OAuthResponse {
	private String id;
	private String access_token;
	private String refresh_token;
	private String instance_url;
	private String issued_at;
	private String signature;
	
    /**
     * The 'id' field actually contains a URL that contains data about the user in this org.
     * Follow this URL to learn about the user.
     * @return
     * @throws MalformedURLException
     */
	public java.net.URL getUserInfo() throws MalformedURLException {
		return new java.net.URL(id);
	}
	
	/**
	 * This is a link to information about your user in Salesforce.
	 * @return
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @return An access token that acts as a Salesforce session ID.
	 */
	public String getAccessToken() {
		return access_token;
	}
	
	/**
	 * See also: http://wiki.developerforce.com/page/Digging_Deeper_into_OAuth_2.0_on_Force.com#Token_Refresh
	 * Refresh token may be null on certain OAuth2 flows, like username and password flow.
	 * 
	 * @return A long-lived token that may be used to obtain a fresh access token on expiry of the access token in this response.
	 */
	public String getRefreshToken() {
		return refresh_token;
	}
	
	/**
	 * @return The instance that is aware of your session.
	 */
	public String getInstanceUrl() {
		return instance_url;
	}
	
	/**
	 * @return The number of seconds since the Unix epoch (00:00:00 UTC on 1 January 1970).
	 */
	public String getIssuedAt() {
		return issued_at;
	}
	
	/**
	 * @return The time of token issue
	 */
	public long getIssuedAtMilliseconds() {
		return Long.valueOf(getIssuedAt()).longValue();
	}
	
	/**
	 * @return The time of token issue
	 */
	public Date getIssuedAtDate() {
		return new Date(getIssuedAtMilliseconds());
	}
	
	/**
	 * Signature signed with the consumer's private key containing the concatenated ID and issued_at. 
	 * This can be used to verify the identity URL was not modified since it was sent by the server.
	 * 
	 * @return Base64-encoded HMAC-SHA256 signature 
	 */
	public String getSignature() {
		return signature;
	}
	
	/*
	 * These setters should not be used as these are responses from the API.
	 * The JSON deserializer uses reflection to set these fields.
	 */
	
	protected void setId(String id) {
		this.id = id;
	}
	protected void setAccessToken(String access_token) {
		this.access_token = access_token;
	}
	protected void setInstanceUrl(String instance_url) {
		this.instance_url = instance_url;
	}
	protected void setIssuedAt(String issued_at) {
		this.issued_at = issued_at;
	}
	protected void setSignature(String signature) {
		this.signature = signature;
	}
	protected void setRefreshToken(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	@Override
	public String toString() {
		return "OAuthResponse [id=" + id + ", access_token=" + access_token
				+ ", refresh_token=" + refresh_token + ", instance_url="
				+ instance_url + ", issued_at=" + issued_at + ", signature="
				+ signature + "]";
	}
}
