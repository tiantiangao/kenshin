package com.gtt.kenshin.oauth.impl.response;

/**
 * @author tiantiangao
 */
public class HttpResponse {

	private String body;
	private String contentType;
	private int responseCode;

	public HttpResponse(String contentType, String body, int responseCode) {
		this.contentType = contentType;
		this.body = body;
		this.responseCode = responseCode;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
}
