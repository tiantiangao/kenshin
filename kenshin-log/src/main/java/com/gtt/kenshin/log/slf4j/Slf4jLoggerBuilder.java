package com.gtt.kenshin.log.slf4j;

import com.gtt.kenshin.log.KenshinLoggerBuilder;
import com.gtt.kenshin.log.KensinLogger;

/**
 * 日志生成器的Slf4j实现
 * 
 * @author tiantian.gao
 * 
 */
public class Slf4jLoggerBuilder implements KenshinLoggerBuilder {

	@Override
	public KensinLogger buildLogger(String name) {
		return new Slf4jLogger(name);
	}

}
