package com.gtt.kenshin.oauth.impl.request;

import com.google.common.collect.Maps;

import java.util.Map;

import static com.gtt.kenshin.oauth.impl.util.OAuthConstants.PARAM.*;

/**
 * @author tiantiangao
 */
public class OAuthAccessTokenRequestBuilder {

	private String tokenUri;
	private Map<String, String> params = Maps.newHashMap();

	public OAuthAccessTokenRequestBuilder(String tokenUri) {
		this.tokenUri = tokenUri;
	}

	public OAuthAccessTokenRequestBuilder setGrantType(String grantType) {
		this.params.put(GRANT_TYPE, grantType);
		return this;
	}

	public OAuthAccessTokenRequestBuilder setClientID(String clientID) {
		this.params.put(CLIENT_ID, clientID);
		return this;
	}

	public OAuthAccessTokenRequestBuilder setClientSecret(String clientSecret) {
		this.params.put(CLIENT_SECRET, clientSecret);
		return this;
	}

	public OAuthAccessTokenRequestBuilder setCode(String code) {
		this.params.put(CODE, code);
		return this;
	}

	public OAuthAccessTokenRequestBuilder setRedir(String redir) {
		this.params.put(REDIRECT_URI, redir);
		return this;
	}

	public HttpRequest build() {
		HttpRequest request = new HttpRequest(tokenUri);
		request.setHeaders(params);
		return request;
	}
}
