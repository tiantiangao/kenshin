package com.gtt.kenshin.oauth.impl.response.base;

import com.gtt.kenshin.oauth.impl.response.base.OAuthResponse;
import com.gtt.kenshin.oauth.impl.util.JsonUtils;

import java.util.Map;

/**
 * json格式的响应
 *
 * @author tiantiangao
 */
public class OAuthJsonResponse implements OAuthResponse {

	private final Map<String, Object> parameters;

	public OAuthJsonResponse(String message) {
		parameters = JsonUtils.parseJSON(message);
	}

	@Override
	public String getValue(String key) {
		Object value = parameters.get(key);
		return value == null ? null : String.valueOf(value);
	}

}
