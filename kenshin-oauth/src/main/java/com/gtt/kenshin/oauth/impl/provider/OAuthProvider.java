package com.gtt.kenshin.oauth.impl.provider;


import com.gtt.kenshin.oauth.ThirdUserInfo;

import javax.servlet.http.HttpServletRequest;

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

}
