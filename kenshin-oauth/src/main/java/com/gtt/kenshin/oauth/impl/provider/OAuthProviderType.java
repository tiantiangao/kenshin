package com.gtt.kenshin.oauth.impl.provider;

/**
 * OAuth提供方类型
 *
 * @author tiantiangao
 */
public interface OAuthProviderType {

	/**
	 * OAuth提供方名称
	 *
	 * @return
	 */
	String getName();

	/**
	 * OAuth授权URI
	 *
	 * @return
	 */
	String getAuthorizeUri();

	/**
	 * OAuth获取access token uri
	 *
	 * @return
	 */
	String getAccessTokenUri();

}
