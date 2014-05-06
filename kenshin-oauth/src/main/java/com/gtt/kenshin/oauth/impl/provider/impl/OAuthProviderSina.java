package com.gtt.kenshin.oauth.impl.provider.impl;

import com.gtt.kenshin.oauth.ThirdUserInfo;
import com.gtt.kenshin.oauth.impl.exception.OAuthSystemException;
import com.gtt.kenshin.oauth.impl.provider.OAuthProvider;
import com.gtt.kenshin.oauth.impl.provider.OAuthProviderApp;
import com.gtt.kenshin.oauth.impl.provider.OAuthProviderType;
import com.gtt.kenshin.oauth.impl.request.HttpRequest;
import com.gtt.kenshin.oauth.impl.request.OAuthAccessTokenRequestBuilder;
import com.gtt.kenshin.oauth.impl.request.OAuthAuthorizeRequestBuilder;
import com.gtt.kenshin.oauth.impl.response.HttpResponse;
import com.gtt.kenshin.oauth.impl.response.OAuthCodeResponse;
import com.gtt.kenshin.oauth.impl.response.OAuthSinaAccessTokenResponse;
import com.gtt.kenshin.oauth.impl.response.base.OAuthJsonResponse;
import com.gtt.kenshin.oauth.impl.util.HttpClient;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.gtt.kenshin.oauth.impl.util.OAuthConstants.GrantType.AUTHORIZATION_CODE;
import static com.gtt.kenshin.oauth.impl.util.OAuthConstants.HttpMethod.GET;
import static com.gtt.kenshin.oauth.impl.util.OAuthConstants.HttpMethod.POST;
import static com.gtt.kenshin.oauth.impl.util.OAuthConstants.PARAM.ACCESS_TOKEN;
import static com.gtt.kenshin.oauth.impl.util.OAuthConstants.PARAM.UID;
import static com.gtt.kenshin.oauth.impl.util.OAuthConstants.ResponseType.CODE;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * @author tiantiangao
 */
public class OAuthProviderSina implements OAuthProvider {

	private static final String URL_SINA_GET_USER_INFO = "https://api.weibo.com/2/users/show.json";

	private static final OAuthProviderType type = new OAuthProviderTypeSina();
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
				.setClientID(oAuthProviderApp.getClientID()).setScope("get_user_info").setRedir(redirectUri)
				.setState(state).buildLocationUri();
	}

	@Override
	public ThirdUserInfo getUserInfo(HttpServletRequest request, String redirectUri) {
		try {
			// get code
			OAuthCodeResponse codeResponse = new OAuthCodeResponse(request);

			// get access token and uid
			OAuthSinaAccessTokenResponse sinaAccessTokenResponse =
					fetchAccessToken(codeResponse.getCode(), redirectUri);


			ThirdUserInfo thirdUserInfo = new ThirdUserInfo();
			thirdUserInfo.setAccessToken(sinaAccessTokenResponse.getAccessToken());
			thirdUserInfo.setThirdUserId(sinaAccessTokenResponse.getThirdID());
			return thirdUserInfo;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getUserNickname(ThirdUserInfo thirdUserInfo) {
		Map<String, Object> response = invoke(URL_SINA_GET_USER_INFO, thirdUserInfo, null, false);
		return response != null ? (String) response.get("screen_name") : null;
	}

	@Override
	public Map<String, Object> invoke(String url, ThirdUserInfo thirdUserInfo, Map<String, String> params,
									  boolean isPost) {
		try {
			checkArgument(isNotBlank(url));
			checkNotNull(thirdUserInfo);
			checkArgument(isNotBlank(thirdUserInfo.getAccessToken()));
			checkArgument(isNotBlank(thirdUserInfo.getThirdUserId()));

			HttpRequest request = new HttpRequest(url);
			request.addHeader(ACCESS_TOKEN, thirdUserInfo.getAccessToken());
			request.addHeader(UID, thirdUserInfo.getThirdUserId());

			if (params != null) {
				for (String key : params.keySet()) {
					request.addHeader(key, params.get(key));
				}
			}

			HttpResponse response = HttpClient.execute(request, isPost ? POST : GET);
			checkNotNull(response);
			checkArgument(isNotBlank(response.getBody()));

			OAuthJsonResponse jsonResponse = new OAuthJsonResponse(response.getBody());
			checkNotNull(jsonResponse);
			return jsonResponse.getValues();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取access token信息
	 *
	 * @return
	 * @throws com.gtt.kenshin.oauth.impl.exception.OAuthSystemException
	 */
	public OAuthSinaAccessTokenResponse fetchAccessToken(String code, String redirectUri) throws OAuthSystemException {
		HttpRequest req =
				new OAuthAccessTokenRequestBuilder(type.getAccessTokenUri()).setClientID(oAuthProviderApp.getClientID())
						.setClientSecret(oAuthProviderApp.getClientSecret()).setCode(code)
						.setGrantType(AUTHORIZATION_CODE).setRedir(redirectUri).build();

		HttpResponse response = HttpClient.executePostWithGetUri(req, POST);
		checkNotNull(response);
		checkArgument(isNotBlank(response.getBody()));

		OAuthSinaAccessTokenResponse tokenResponse = new OAuthSinaAccessTokenResponse(response.getBody());
		checkArgument(isNotBlank(tokenResponse.getAccessToken()));
		checkArgument(isNotBlank(tokenResponse.getThirdID()));

		return tokenResponse;
	}

	public void setoAuthProviderApp(OAuthProviderApp oAuthProviderApp) {
		this.oAuthProviderApp = oAuthProviderApp;
	}
}
