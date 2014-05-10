package com.gtt.kenshin.oauth.impl.util;

/**
 * @author tiantiangao
 */
public interface LogProxy {

	void info(String msg);

	void err(String msg, Exception e);

}
