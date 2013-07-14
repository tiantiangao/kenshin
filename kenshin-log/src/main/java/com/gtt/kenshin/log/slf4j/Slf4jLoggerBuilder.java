package com.gtt.kenshin.log.slf4j;

import com.gtt.kenshin.log.KenshinLogger;
import com.gtt.kenshin.log.KenshinLoggerBuilder;

/**
 * 日志生成器的Slf4j实现
 * 
 * @author tiantian.gao
 * 
 */
public class Slf4jLoggerBuilder implements KenshinLoggerBuilder {

	@Override
	public KenshinLogger buildLogger(String name) {
		return new Slf4jLogger(name);
	}

}
