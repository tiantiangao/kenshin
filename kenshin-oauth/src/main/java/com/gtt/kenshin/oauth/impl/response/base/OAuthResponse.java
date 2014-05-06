package com.gtt.kenshin.oauth.impl.response.base;

import java.util.Map;

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

	/**
	 * 获取所有key的值
	 *
	 * @return
	 */
	Map<String, Object> getValues();

}
