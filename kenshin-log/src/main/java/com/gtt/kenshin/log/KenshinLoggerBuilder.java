package com.gtt.kenshin.log;


/**
 * 日志生成器
 * 
 * @author tiantian.gao
 * 
 */
public interface KenshinLoggerBuilder {

	/**
	 * 根据名称生成日志实例
	 * 
	 * @param name
	 * @return
	 */
	KenshinLogger buildLogger(String name);

}
