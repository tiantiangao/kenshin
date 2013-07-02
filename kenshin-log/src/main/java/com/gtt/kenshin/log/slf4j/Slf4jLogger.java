package com.gtt.kenshin.log.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gtt.kenshin.log.AbstractKenshinLogger;

/**
 * 日志的Slf4j实现
 * 
 * @author tiantian.gao
 * 
 */
public class Slf4jLogger extends AbstractKenshinLogger {

	/**
	 * All log actions will be delegated to Slf4j
	 */
	private final Logger logger;

	public Slf4jLogger(String name) {
		this.logger = LoggerFactory.getLogger(name);
	}

	@Override
	protected void doDebug(String message) {
		logger.debug(message);
	}

	@Override
	protected void doDebug(String message, Throwable t) {
		logger.debug(message, t);
	}

	@Override
	protected void doInfo(String message) {
		logger.info(message);
	}

	@Override
	protected void doInfo(String message, Throwable t) {
		logger.info(message, t);
	}

	@Override
	protected void doWarn(String message) {
		logger.warn(message);
	}

	@Override
	protected void doWarn(String message, Throwable t) {
		logger.warn(message, t);
	}

	@Override
	protected void doError(String message) {
		logger.error(message);
	}

	@Override
	protected void doError(String message, Throwable t) {
		logger.error(message, t);
	}
}
