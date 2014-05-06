package com.gtt.kenshin.oauth.impl.exception;

/**
 * @author tiantiangao
 */
public class OAuthSystemException extends Exception {

	public OAuthSystemException() {
		super();
	}

	public OAuthSystemException(String s) {
		super(s);
	}

	public OAuthSystemException(Throwable throwable) {
		super(throwable);
	}

	public OAuthSystemException(String s, Throwable throwable) {
		super(s, throwable);
	}
}
