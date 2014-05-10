package com.gtt.kenshin.oauth.impl.util;

/**
 * @author tiantiangao
 */
public class LogProxyDefaultImpl implements LogProxy {

	@Override
	public void info(String msg) {
		System.out.println(msg);
	}

	@Override
	public void err(String msg, Exception e) {
		System.err.println("msg");
		e.printStackTrace();
	}
}
