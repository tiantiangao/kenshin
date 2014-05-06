package com.gtt.kenshin.oauth.impl.provider.impl;


import com.gtt.kenshin.oauth.ThirdUserInfo;
import com.gtt.kenshin.oauth.impl.exception.OAuthSystemException;
import com.gtt.kenshin.oauth.impl.provider.OAuthProvider;
import com.gtt.kenshin.oauth.impl.provider.OAuthProviderApp;
import com.gtt.kenshin.oauth.impl.provider.OAuthProviderType;
import com.gtt.kenshin.oauth.impl.request.HttpRequest;
import com.gtt.kenshin.oauth.impl.request.OAuthAccessTokenRequestBuilder;
import com.gtt.kenshin.oauth.impl.request.OAuthAuthorizeRequestBuilder;
import com.gtt.kenshin.oauth.impl.response.*;
import com.gtt.kenshin.oauth.impl.util.HttpClient;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.gtt.kenshin.oauth.impl.util.OAuthConstants.GrantType.AUTHORIZATION_CODE;
import static com.gtt.kenshin.oauth.impl.util.OAuthConstants.HttpMethod.GET;
import static com.gtt.kenshin.oauth.impl.util.OAuthConstants.PARAM.ACCESS_TOKEN;
import static com.gtt.kenshin.oauth.impl.util.OAuthConstants.ResponseType.CODE;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * @author tiantiangao
 */
public class OAuthProviderQQ implements OAuthProvider {

	private static final OAuthProviderType type = new OAuthProviderTypeQQ();
	private static final String URL_QQ_GET_OPENID = "https://graph.qq.com/oauth2.0/me";
	private OAuthProviderApp oAuthProviderApp;

	@Override
	public OAuthProviderType getProviderType() {
		return type;
	}

	@Override
	public OAuthProviderApp getProviderApp() {
		return oAuthProviderApp;
	}

	@Override
	public String buildAuthorizeRequest(String redirectUri, String state) {
		return new OAuthAuthorizeRequestBuilder(type.getAuthorizeUri()).setResponseType(CODE)
				.setClientID(oAuthProviderApp.getClientID()).setScope("all").setRedir(redirectUri).setState(state)
				.buildLocationUri();
	}

	@Override
	public ThirdUserInfo getUserInfo(HttpServletRequest request, String redirectUri) {
		try {
			// get code
			OAuthCodeResponse codeResponse = new OAuthCodeResponse(request);

			// get access token
			OAuthAccessToken accessToken = fetchAccessToken(codeResponse.getCode(), redirectUri);

			// get open id
			OAuthOpenID openID = fetchOpenID(accessToken.getAccessToken());

			ThirdUserInfo thirdUserInfo = new ThirdUserInfo();
			thirdUserInfo.setAccessToken(accessToken.getAccessToken());
			thirdUserInfo.setThirdUserId(openID.getThirdID());
			return thirdUserInfo;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取QQ的OpenID
	 *
	 * @param accessToken
	 * @return
	 * @throws OAuthSystemException
	 */
	private OAuthOpenID fetchOpenID(String accessToken) throws OAuthSystemException {
		HttpRequest req = new HttpRequest(URL_QQ_GET_OPENID);
		req.addHeader(ACCESS_TOKEN, accessToken);
		HttpResponse response = HttpClient.execute(req, GET);
		checkNotNull(response);
		checkArgument(isNotBlank(response.getBody()));


		OAuthQQOpenIDResponse openIDResponse = new OAuthQQOpenIDResponse(filterResponse(response.getBody()));
		checkArgument(isNotBlank(openIDResponse.getThirdID()));
		return openIDResponse;
	}

	/**
	 * 过滤OpenID请求返回的响应中的"callback"，转换为标准的json格式
	 *
	 * @param body
	 * @return
	 */
	private String filterResponse(String body) {
		String resp = body;
		if (StringUtils.contains(resp, "(")) {
			resp = StringUtils.substringAfter(body, "(");
		}
		if (StringUtils.contains(resp, ")")) {
			resp = StringUtils.substringBeforeLast(resp, ")");
		}
		return resp.trim();
	}

	/**
	 * 获取access token信息
	 *
	 * @return
	 * @throws OAuthSystemException
	 */
	private OAuthAccessToken fetchAccessToken(String code, String redirectUri) throws OAuthSystemException {
		HttpRequest req =
				new OAuthAccessTokenRequestBuilder(type.getAccessTokenUri()).setClientID(oAuthProviderApp.getClientID())
						.setClientSecret(oAuthProviderApp.getClientSecret()).setCode(code)
						.setGrantType(AUTHORIZATION_CODE).setRedir(redirectUri).build();

		HttpResponse response = HttpClient.execute(req, GET);
		checkNotNull(response);
		checkArgument(isNotBlank(response.getBody()));

		OAuthQQAccessTokenResponse tokenResponse = new OAuthQQAccessTokenResponse(response.getBody());
		checkArgument(isNotBlank(tokenResponse.getAccessToken()));

		return tokenResponse;
	}

	public void setoAuthProviderApp(OAuthProviderApp oAuthProviderApp) {
		this.oAuthProviderApp = oAuthProviderApp;
	}
}
