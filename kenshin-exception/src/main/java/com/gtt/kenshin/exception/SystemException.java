package com.gtt.kenshin.exception;

/**
 * 系统异常基类
 * 
 * @author tiantian.gao
 * 
 */
public class SystemException extends RuntimeException {

	private static final long serialVersionUID = 4108976055467870103L;

	public SystemException() {
		super();
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public SystemException(Throwable cause) {
		super(cause);
	}
}
