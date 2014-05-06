package com.gtt.kenshin.oauth.impl.provider;

/**
 * @author tiantiangao
 */
public interface OAuthProviderApp {

	/**
	 * 第三方OAuth平台申请的ClientID(AppID)
	 *
	 * @return
	 */
	String getClientID();

	/**
	 * 第三方OAuth平台申请的ClientSecret(AppKey/AppSecret)
	 *
	 * @return
	 */
	String getClientSecret();

}
