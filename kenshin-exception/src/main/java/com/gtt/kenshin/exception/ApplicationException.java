package com.gtt.kenshin.exception;

/**
 * 应用异常基类
 * 
 * @author tiantian.gao
 * 
 */
public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 7632712693564735124L;

	public ApplicationException() {
		super();
	}

	public ApplicationException(String message) {
		super(message);
	}

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApplicationException(Throwable cause) {
		super(cause);
	}
}
