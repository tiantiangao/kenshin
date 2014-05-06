package com.gtt.kenshin.oauth.impl.provider;


import com.gtt.kenshin.oauth.ThirdUserInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author tiantiangao
 */
public interface OAuthProvider {

	/**
	 * 获取提供方类型
	 *
	 * @return
	 */
	OAuthProviderType getProviderType();

	/**
	 * 获取提供方应用信息
	 *
	 * @return
	 */
	OAuthProviderApp getProviderApp();

	/**
	 * 构建授权请求
	 *
	 * @param redirectUri
	 * @param state
	 * @return
	 */
	String buildAuthorizeRequest(String redirectUri, String state);

	/**
	 * 获取用户信息
	 *
	 * @param request
	 * @param redirectUri
	 * @return
	 */
	ThirdUserInfo getUserInfo(HttpServletRequest request, String redirectUri);

	/**
	 * 获取用户昵称
	 *
	 * @param thirdUserInfo
	 * @return
	 */
	String getUserNickname(ThirdUserInfo thirdUserInfo);

	/**
	 * 调用一个第三方接口
	 *
	 * @param url
	 * @param thirdUserInfo
	 * @param params
	 * @param isPost
	 * @return
	 */
	Map<String, Object> invoke(String url, ThirdUserInfo thirdUserInfo, Map<String, String> params, boolean isPost);

}
