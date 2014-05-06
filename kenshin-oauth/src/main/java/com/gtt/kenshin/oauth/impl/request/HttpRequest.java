package com.gtt.kenshin.oauth.impl.request;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author tiantiangao
 */
public class HttpRequest {

	protected String url;
	protected String body;
	protected Map<String, String> headers;

	public HttpRequest(String url) {
		this.url = url;
		this.headers = Maps.newHashMap();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public String getHeader(String name) {
		return this.headers.get(name);
	}

	public void addHeader(String name, String header) {
		this.headers.put(name, header);
	}

}
