package com.gtt.kenshin.oauth.impl.response;

import com.gtt.kenshin.oauth.impl.response.base.OAuthJsonResponse;

import static com.gtt.kenshin.oauth.impl.util.OAuthConstants.PARAM.*;

/**
 * @author tiantiangao
 */
public class OAuthSinaAccessTokenResponse extends OAuthJsonResponse implements OAuthAccessToken, OAuthOpenID {

	public OAuthSinaAccessTokenResponse(String message) {
		super(message);
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

	@Override
	public String getThirdID() {
		return getValue(UID);
	}
}
