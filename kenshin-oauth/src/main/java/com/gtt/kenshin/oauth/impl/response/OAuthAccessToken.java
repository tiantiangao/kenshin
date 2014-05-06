package com.gtt.kenshin.oauth.impl.response;

/**
 * @author tiantiangao
 */
public interface OAuthAccessToken {

	String getAccessToken();

	Long getExpiresIn();

	String getRefreshToken();

}
