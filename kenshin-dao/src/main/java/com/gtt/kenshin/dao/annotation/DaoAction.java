package com.gtt.kenshin.dao.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * DAO方法注解
 * 
 * @author tiantian.gao
 * @date 2011-5-24
 * 
 */
@Target(value = { ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DaoAction {

	/**
	 * DAO方法类型
	 * 
	 * @return
	 */
	DaoActionType action() default DaoActionType.QUERY;

	/**
	 * DAO方法名称
	 * 
	 * @return
	 */
	String method() default "";

	// /**
	// * 存储过程的调用结果类型
	// */
	// CallResultType callResult() default CallResultType.Null;
}
