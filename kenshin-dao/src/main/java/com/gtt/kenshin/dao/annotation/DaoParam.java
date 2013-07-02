package com.gtt.kenshin.dao.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * DAO参数注释
 * 
 * @author tiantian.gao
 * @date 2011-5-24
 * 
 */
@Target(value = { ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DaoParam {

	/**
	 * 参数名称
	 */
	String value() default "";

	/**
	 * 参数名称类型,默认从注释中获取参数名称
	 */
	DaoParamType type() default DaoParamType.NORMAL;
}
