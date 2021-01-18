package havis.net.rest.middleware.utils.model;

public class Uri {

	private String uri;
	private String scheme;
	private String schemeSpecificPart;
	private String authority;
	private String userInfo;
	private String host;
	private int port;
	private String path;
	private String query;
	private String rawQuery;
	private String fragment;

	public Uri() {
	}

	/**
	 * Default Constructor. The Constructor won't analyze the uri and paste the
	 * parts in the parameters.
	 * 
	 * @param uri
	 */
	public Uri(String uri) {
		this.uri = uri;
	}

	/**
	 * Default constructor. The Constructor won't create the uri string.
	 * 
	 * @param scheme
	 * @param schemeSpecificPart
	 * @param authority
	 * @param userInfo
	 * @param host
	 * @param port
	 * @param path
	 * @param query
	 * @param rawQuery
	 * @param fragment
	 */
	public Uri(String scheme, String schemeSpecificPart, String authority, String userInfo, String host, int port, String path, String query, String rawQuery, String fragment) {
		this.scheme = scheme;
		this.schemeSpecificPart = schemeSpecificPart;
		this.authority = authority;
		this.userInfo = userInfo;
		this.host = host;
		this.port = port;
		this.path = path;
		this.query = query;
		this.rawQuery = rawQuery;
		this.fragment = fragment;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getSchemeSpecificPart() {
		return schemeSpecificPart;
	}

	public void setSchemeSpecificPart(String schemeSpecificPart) {
		this.schemeSpecificPart = schemeSpecificPart;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getRawQuery() {
		return rawQuery;
	}

	public void setRawQuery(String rawQuery) {
		this.rawQuery = rawQuery;
	}

	public String getFragment() {
		return fragment;
	}

	public void setFragment(String fragment) {
		this.fragment = fragment;
	}
}