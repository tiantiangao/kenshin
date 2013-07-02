package com.gtt.accounting.persistence.enums.base;

/**
 * 整型的枚举接口类
 * 
 * @author tiantian.gao
 * @date 2011-7-4
 * 
 */
public interface BaseIntEnum<T> extends BaseEnum {

	int getCode();

	T fromCode(int code);
}
