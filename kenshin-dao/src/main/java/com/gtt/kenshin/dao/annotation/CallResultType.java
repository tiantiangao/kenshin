package com.gtt.kenshin.dao.annotation;

/**
 * 存储过程的调用结果类型
 * 
 * @author tiantian.gao
 * @date 2011-5-24
 * 
 */
public enum CallResultType {

	Null, // determined by method metadata

	None, // no procedure result

	Single, // single procedure result

	List; // multiple procedure result

}
