package com.gtt.kenshin.oauth.impl.util;

/**
 * OAuth相关常量类
 *
 * @author tiantiangao
 */
public class OAuthConstants {

	public static final String MARK_QUESTION = "?";
	public static final String MARK_AND = "&";
	public static final String OAUTH_HEADER_NAME = "Bearer";

	public static final class ContentType {
		public static final String URL_ENCODED = "application/x-www-form-urlencoded";
		public static final String JSON = "application/json";
	}

	public static final class ResponseType {
		public static final String CODE = "code";
		public static final String TOKEN = "token";
	}

	public static final class GrantType {
		public static final String AUTHORIZATION_CODE = "authorization_code";
		public static final String REFRESH_TOKEN = "refresh_token";
	}

	public static final class PARAM {
		public static final String RESPONSE_TYPE = "response_type";
		public static final String CLIENT_ID = "client_id";
		public static final String SCOPE = "scope";
		public static final String STATE = "state";
		public static final String CODE = "code";
		public static final String REDIRECT_URI = "redirect_uri";
		public static final String GRANT_TYPE = "grant_type";
		public static final String CLIENT_SECRET = "client_secret";
		public static final String ACCESS_TOKEN = "access_token";
		public static final String EXPIRES_IN = "expires_in";
		public static final String REFRESH_TOKEN = "refresh_token";

		public static final String OPENID = "openid";
		public static final String UID = "uid";

	}

	public static final class HttpMethod {
		public static final String POST = "POST";
		public static final String GET = "GET";
		public static final String DELETE = "DELETE";
		public static final String PUT = "PUT";
	}
}
