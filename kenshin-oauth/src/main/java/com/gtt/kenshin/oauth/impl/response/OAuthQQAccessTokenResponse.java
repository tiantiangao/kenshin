package com.gtt.kenshin.oauth.impl.response;

import com.gtt.kenshin.oauth.impl.response.base.OAuthQueryStringResponse;

import static com.gtt.kenshin.oauth.impl.util.OAuthConstants.PARAM.*;

/**
 * @author tiantiangao
 */
public class OAuthQQAccessTokenResponse extends OAuthQueryStringResponse implements OAuthAccessToken {

	public OAuthQQAccessTokenResponse(String queryString) {
		super(queryString);
	}

	@Override
	public String getAccessToken() {
		return getValue(ACCESS_TOKEN);
	}

	@Override
	public Long getExpiresIn() {
		String value = getValue(EXPIRES_IN);
		return value == null ? null : Long.valueOf(value);
	}

	@Override
	public String getRefreshToken() {
		return getValue(REFRESH_TOKEN);
	}
}
