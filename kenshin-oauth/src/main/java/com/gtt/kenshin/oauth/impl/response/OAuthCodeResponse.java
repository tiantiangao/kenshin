package com.gtt.kenshin.oauth.impl.response;

import com.gtt.kenshin.oauth.impl.response.base.OAuthRequestResponse;

import javax.servlet.http.HttpServletRequest;

import static com.gtt.kenshin.oauth.impl.util.OAuthConstants.PARAM.CODE;

/**
 * @author tiantiangao
 */
public class OAuthCodeResponse extends OAuthRequestResponse {

	public OAuthCodeResponse(HttpServletRequest request) {
		super(request);
	}

	public String getCode() {
		return getValue(CODE);
	}
}
