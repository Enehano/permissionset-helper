package oauth2.model;

/**
 * POJO used for deserializing JSON response from Authorization server.
 * 
 * @author gwester
 */
public class UserInformation {
	private String id; //"https://login.salesforce.com/id/00D50000000IZ3ZEAW/00550000001fg5OAAQ",
	private Boolean asserted_user;
	private String user_id;
	private String organization_id;
	private String username;
	private String nick_name;
	private String display_name;
	private String email;
	private Boolean active;
	private String user_type; //"STANDARD"
	private String language; //"en_US"
    private String locale; //en_US"
    private int utcOffset; //-28800000
    private String last_modified_date; // 2011-01-14T23:28:01.000+0000
	
	private Status status;
    
	public static class Status {
		private String created_date; // 2010-11-08T20:55:33.000+0000
		private String body;
		
		public String getCreated_date() {
			return created_date;
		}
		public String getBody() {
			return body;
		}
		protected void setCreated_date(String created_date) {
			this.created_date = created_date;
		}
		protected void setBody(String body) {
			this.body = body;
		}
		
		@Override
		public String toString() {
			return "Status [created_date=" + created_date + ", body=" + body
					+ "]";
		}
	}
	
	private PhotoInformation photos; 
	
	public static class PhotoInformation {
		private String picture; //"https://c.na1.content.force.com/profilephoto/005/F"
		private String thumbnail; //"https://c.na1.content.force.com/profilephoto/005/T"
		
		public String getPicture() {
			return picture;
		}
		public String getThumbnail() {
			return thumbnail;
		}
		protected void setPicture(String picture) {
			this.picture = picture;
		}
		protected void setThumbnail(String thumbnail) {
			this.thumbnail = thumbnail;
		}
		
		@Override
		public String toString() {
			return "PhotoInformation [picture=" + picture + ", thumbnail="
					+ thumbnail + "]";
		} 
	}
	
	private SalesforceApiUrls urls;
	
	public static class SalesforceApiUrls {
		private String enterprise; //https://na1.salesforce.com/services/Soap/c/{version}/00D50000000IZ3Z
		private String metadata; //https://na1.salesforce.com/services/Soap/m/{version}/00D50000000IZ3Z
		private String partner; //https://na1.salesforce.com/services/Soap/u/{version}/00D50000000IZ3Z
		private String rest; //https://na1.salesforce.com/services/data/v{version}/
		private String sobjects; //https://na1.salesforce.com/services/data/v{version}/sobjects/
		private String search; //https://na1.salesforce.com/services/data/v{version}/search/
		private String query; //https://na1.salesforce.com/services/data/v{version}/query/
		private String recent; //https://na1.salesforce.com/services/data/v{version}/recent/
		private String profile; //https://na1.salesforce.com/00550000001fg5OAAQ
		
		public String getEnterprise() {
			return enterprise;
		}
		public String getMetadata() {
			return metadata;
		}
		public String getPartner() {
			return partner;
		}
		public String getRest() {
			return rest;
		}
		public String getSobjects() {
			return sobjects;
		}
		public String getSearch() {
			return search;
		}
		public String getQuery() {
			return query;
		}
		public String getRecent() {
			return recent;
		}
		public String getProfile() {
			return profile;
		}
		protected void setEnterprise(String enterprise) {
			this.enterprise = enterprise;
		}
		protected void setMetadata(String metadata) {
			this.metadata = metadata;
		}
		protected void setPartner(String partner) {
			this.partner = partner;
		}
		protected void setRest(String rest) {
			this.rest = rest;
		}
		protected void setSobjects(String sobjects) {
			this.sobjects = sobjects;
		}
		protected void setSearch(String search) {
			this.search = search;
		}
		protected void setQuery(String query) {
			this.query = query;
		}
		protected void setRecent(String recent) {
			this.recent = recent;
		}
		protected void setProfile(String profile) {
			this.profile = profile;
		}
		
		@Override
		public String toString() {
			return "SalesforceApiUrls [enterprise=" + enterprise
					+ ", metadata=" + metadata + ", partner=" + partner
					+ ", rest=" + rest + ", sobjects=" + sobjects + ", search="
					+ search + ", query=" + query + ", recent=" + recent
					+ ", profile=" + profile + "]";
		}
	}

	public String getId() {
		return id;
	}

	public Boolean getAssertedUser() {
		return asserted_user;
	}

	public String getOrganizationId() {
		return organization_id;
	}

	public String getUserId() {
		return user_id;
	}

	public String getUsername() {
		return username;
	}

	public String getNickname() {
		return nick_name;
	}

	public String getDisplayName() {
		return display_name;
	}

	public String getEmail() {
		return email;
	}

	public Boolean getActive() {
		return active;
	}

	public String getUserType() {
		return user_type;
	}

	public String getLanguage() {
		return language;
	}

	public String getLocale() {
		return locale;
	}

	public int getUtcOffset() {
		return utcOffset;
	}

	public String getLastModifiedDate() {
		return last_modified_date;
	}

	public Status getStatus() {
		return status;
	}

	public PhotoInformation getPhotos() {
		return photos;
	}

	public SalesforceApiUrls getUrls() {
		return urls;
	}

	protected void setId(String id) {
		this.id = id;
	}

	protected void setAssertedUser(Boolean asserted_user) {
		this.asserted_user = asserted_user;
	}

	protected void setOrganizationId(String organization_id) {
		this.organization_id = organization_id;
	}

	protected void setUsername(String username) {
		this.username = username;
	}

	protected void setNickname(String nick_name) {
		this.nick_name = nick_name;
	}

	protected void setDisplayName(String display_name) {
		this.display_name = display_name;
	}

	protected void setEmail(String email) {
		this.email = email;
	}

	protected void setActive(Boolean active) {
		this.active = active;
	}

	protected void setUserType(String user_type) {
		this.user_type = user_type;
	}

	protected void setLanguage(String language) {
		this.language = language;
	}

	protected void setLocale(String locale) {
		this.locale = locale;
	}

	protected void setUtcOffset(int utcOffset) {
		this.utcOffset = utcOffset;
	}

	protected void setLastModifiedDate(String last_modified_date) {
		this.last_modified_date = last_modified_date;
	}

	protected void setStatus(Status status) {
		this.status = status;
	}

	protected void setPhotos(PhotoInformation photos) {
		this.photos = photos;
	}

	protected void setUrls(SalesforceApiUrls urls) {
		this.urls = urls;
	}

	protected void setUserId(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "UserInformation [id=" + id + ", asserted_user=" + asserted_user
				+ ", user_id=" + user_id + ", organization_id="
				+ organization_id + ", username=" + username + ", nick_name="
				+ nick_name + ", display_name=" + display_name + ", email="
				+ email + ", active=" + active + ", user_type=" + user_type
				+ ", language=" + language + ", locale=" + locale
				+ ", utcOffset=" + utcOffset + ", last_modified_date="
				+ last_modified_date + ", status=" + status + ", photos="
				+ photos + ", urls=" + urls + "]";
	}
	
}
