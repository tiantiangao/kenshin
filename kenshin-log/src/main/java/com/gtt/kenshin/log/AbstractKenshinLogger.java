package com.gtt.kenshin.log;


/**
 * 日志抽象类
 * 
 * @author tiantian.gao
 * 
 */
public abstract class AbstractKenshinLogger implements KensinLogger {

	@Override
	public void debug(Object message, Throwable t) {
		doDebug(convertMessage(message), t);
	}

	protected abstract void doDebug(String message, Throwable t);

	@Override
	public void debug(Object message) {
		doDebug(convertMessage(message));
	}

	protected abstract void doDebug(String message);

	@Override
	public void info(Object message, Throwable t) {
		doInfo(convertMessage(message), t);
	}

	protected abstract void doInfo(String message, Throwable t);

	@Override
	public void info(Object message) {
		doInfo(convertMessage(message));
	}

	protected abstract void doInfo(String message);

	@Override
	public void warn(Object message, Throwable t) {
		doWarn(convertMessage(message), t);
	}

	protected abstract void doWarn(String message, Throwable t);

	@Override
	public void warn(Object message) {
		doWarn(convertMessage(message));
	}

	protected abstract void doWarn(String message);

	@Override
	public void error(Object message, Throwable t) {
		doError(convertMessage(message), t);
	}

	protected abstract void doError(String message, Throwable t);

	@Override
	public void error(Object message) {
		doError(convertMessage(message));
	}

	protected abstract void doError(String message);

	private String convertMessage(final Object message) {
		if (message instanceof String) {
			return (String) message;
		} else {
			return message.toString();
		}
	}

}
