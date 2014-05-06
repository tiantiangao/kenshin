package com.gtt.kenshin.oauth;

import javax.servlet.http.HttpServletRequest;

/**
 * 第三方OAuth提供者工厂类
 *
 * @author tiantiangao
 */
public interface OAuthClient {

	String buildAuthorizeRequest(String oauthType, String redir, String state);

	ThirdUserInfo getUserInfo(String oauthType, HttpServletRequest request, String redir);

}
