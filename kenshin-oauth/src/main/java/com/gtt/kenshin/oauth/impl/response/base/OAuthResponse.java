package com.gtt.kenshin.oauth.impl.response.base;

/**
 * @author tiantiangao
 */
public interface OAuthResponse {

	/**
	 * 获取指定key的值
	 *
	 * @param key
	 * @return
	 */
	String getValue(String key);

}
