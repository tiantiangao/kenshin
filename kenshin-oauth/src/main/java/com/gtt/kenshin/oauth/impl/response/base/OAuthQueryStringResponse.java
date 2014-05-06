package com.gtt.kenshin.oauth.impl.response.base;

import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.gtt.kenshin.oauth.impl.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * 参数格式的响应
 *
 * @author tiantiangao
 */
public class OAuthQueryStringResponse implements OAuthResponse {

	private static final String PARAMETER_SEPARATOR = "&";
	private static final String NAME_VALUE_SEPARATOR = "=";

	private final String queryString;
	private final Map<String, String> parameters = Maps.newHashMap();


	public OAuthQueryStringResponse(String queryString) {
		this.queryString = queryString;

		Iterable<String> pairList = Splitter.on(PARAMETER_SEPARATOR).split(queryString);
		for (String pair : pairList) {
			Iterable<String> nameValue = Splitter.on(NAME_VALUE_SEPARATOR).split(pair);
			if (nameValue == null) {
				continue;
			}

			List<String> nameValueList = Lists.newArrayList(nameValue);
			if (CollectionUtils.isEmpty(nameValueList) || nameValueList.size() != 2) {
				continue;
			}

			parameters.put(nameValueList.get(0), nameValueList.get(1));
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
