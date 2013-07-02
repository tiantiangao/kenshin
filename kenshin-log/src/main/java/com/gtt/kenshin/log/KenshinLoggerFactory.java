package com.gtt.kenshin.log;

import java.util.Map;
import java.util.WeakHashMap;

import com.gtt.kenshin.log.slf4j.Slf4jLoggerBuilder;

/**
 * Log工厂
 * 
 * @author tiantian.gao
 * 
 */
public class KenshinLoggerFactory {

	private static KenshinLoggerBuilder builder = new Slf4jLoggerBuilder();// 默认使用slf4j的日志生成器

	private static Map<String, KensinLogger> caches = new WeakHashMap<String, KensinLogger>();

	public static KensinLogger getLogger(Class<?> clazz) {
		return getLogger(clazz.getName());
	}

	private static KensinLogger getLogger(String name) {
		if (name == null) {
			throw new IllegalArgumentException("log name is null.");
		}

		// load cache
		KensinLogger logger = caches.get(name);

		if (logger != null) {
			return logger;
		}

		// new logger
		logger = builder.buildLogger(name);

		// add cache
		caches.put(name, logger);

		return logger;
	}
}
