package com.gtt.accounting.persistence.enums.base;

/**
 * 字符串型枚举接口类
 * 
 * @author tiantian.gao
 * @date 2011-7-4
 * 
 */
public interface BaseStringEnum<T> extends BaseEnum {

	String getCode();

	T fromCode(String code);
}
