package com.gtt.kenshin.oauth.impl.response;

import com.gtt.kenshin.oauth.impl.response.base.OAuthJsonResponse;

import static com.gtt.kenshin.oauth.impl.util.OAuthConstants.PARAM.OPENID;

/**
 * @author tiantiangao
 */
public class OAuthQQOpenIDResponse extends OAuthJsonResponse implements OAuthOpenID {

	public OAuthQQOpenIDResponse(String message) {
		super(message);
	}

	@Override
	public String getThirdID() {
		return getValue(OPENID);
	}
}
