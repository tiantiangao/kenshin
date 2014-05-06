package com.gtt.kenshin.oauth.impl.response.base;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.gtt.kenshin.oauth.impl.util.OAuthUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 参数格式的响应
 *
 * @author tiantiangao
 */
public class OAuthRequestResponse implements OAuthResponse {

	private final HttpServletRequest request;
	private final Map<String, String> parameters = Maps.newHashMap();

	public OAuthRequestResponse(HttpServletRequest request) {
		this.request = request;
		Map<String, String[]> params = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : params.entrySet()) {
			String key = entry.getKey();
			String[] values = entry.getValue();
			if (!OAuthUtils.hasEmptyValues(values)) {
				parameters.put(key, values[0]);
			}
		}
	}

	@Override
	public String getValue(String key) {
		String value = parameters.get(key);
		return value == null ? null : value;
	}

	@Override
	public Map<String, Object> getValues() {
		return Maps.transformValues(parameters, new Function<String, Object>() {
			@Override
			public Object apply(String input) {
				return input;
			}
		});
	}

}
