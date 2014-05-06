package com.gtt.kenshin.oauth;

import javax.servlet.http.HttpServletRequest;

/**
 * 第三方OAuth提供者工厂类
 *
 * @author tiantiangao
 */
public interface OAuthClient {

	/**
	 * 构建用户授权的URL
	 *
	 * @param oauthType 第三方oauth类型
	 * @param redir 第三方授权通过后的重定向地址
	 * @param state 状态值
	 * @return
	 */
	String buildAuthorizeRequest(String oauthType, String redir, String state);

	/**
	 * 获取用户信息
	 *
	 * @param oauthType 第三方oauth类型
	 * @param request 授权完成后，重定向地址的request
	 * @param redir 重定向地址
	 * @return
	 */
	ThirdUserInfo getUserInfo(String oauthType, HttpServletRequest request, String redir);

}
