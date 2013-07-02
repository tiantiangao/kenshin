package com.gtt.kenshin.dao.annotation;

/**
 * DAO参数名称类型
 * 
 * @author tiantian.gao
 * @date 2011-5-24
 * 
 */
public enum DaoParamType {

	/**
	 * 从方法中获取参数名称
	 */
	NONE,
	/**
	 * 从注解中获取参数名称
	 */
	NORMAL
}
