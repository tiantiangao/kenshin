package com.gtt.kenshin.oauth.impl.request;

import com.google.common.collect.Maps;
import com.gtt.kenshin.oauth.impl.util.OAuthUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

import static com.gtt.kenshin.oauth.impl.util.OAuthConstants.MARK_AND;
import static com.gtt.kenshin.oauth.impl.util.OAuthConstants.MARK_QUESTION;
import static com.gtt.kenshin.oauth.impl.util.OAuthConstants.PARAM.*;

/**
 * @author tiantiangao
 */
public class OAuthAuthorizeRequestBuilder {

	private String authorizeUri;
	private Map<String, String> params = Maps.newHashMap();

	public OAuthAuthorizeRequestBuilder(String authorizeUri) {
		this.authorizeUri = authorizeUri;
	}

	public OAuthAuthorizeRequestBuilder setResponseType(String responseType) {
		params.put(RESPONSE_TYPE, responseType);
		return this;
	}

	public OAuthAuthorizeRequestBuilder setClientID(String clientID) {
		params.put(CLIENT_ID, clientID);
		return this;
	}

	public OAuthAuthorizeRequestBuilder setScope(String scope) {
		params.put(SCOPE, scope);
		return this;
	}

	public OAuthAuthorizeRequestBuilder setState(String state) {
		params.put(STATE, state);
		return this;
	}

	public OAuthAuthorizeRequestBuilder setRedir(String redir) {
		params.put(REDIRECT_URI, redir);
		return this;
	}

	public String buildLocationUri() {
		if (StringUtils.isBlank(authorizeUri)) {
			return "";
		}
		boolean containsQuestionMark = authorizeUri.contains(MARK_QUESTION);
		StringBuffer url = new StringBuffer(authorizeUri);

		StringBuffer query = new StringBuffer(OAuthUtils.format(params.entrySet(), "UTF-8"));

		if (StringUtils.isNotBlank(query.toString())) {
			if (containsQuestionMark) {
				url.append(MARK_AND).append(query);
			} else {
				url.append(MARK_QUESTION).append(query);
			}
		}
		return url.toString();
	}
}
