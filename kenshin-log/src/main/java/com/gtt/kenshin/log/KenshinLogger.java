package com.gtt.kenshin.log;

/**
 * Log接口
 * 
 * @author tiantian.gao
 * 
 */
public interface KenshinLogger {

	/**
	 * Debug level message
	 */
	void debug(Object message, Throwable t);

	/**
	 * Debug level message
	 */
	void debug(Object message);

	/**
	 * Warn level message
	 */
	void warn(Object message, Throwable t);

	/**
	 * Warn level message
	 */
	void warn(Object message);

	/**
	 * Error level message
	 */
	void error(Object message, Throwable t);

	/**
	 * Error level message
	 */
	void error(Object message);

	/**
	 * Info level message
	 */
	void info(Object message, Throwable t);

	/**
	 * Info level message
	 */
	void info(Object message);

}
