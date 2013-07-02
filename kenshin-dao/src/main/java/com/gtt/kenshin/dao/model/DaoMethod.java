package com.gtt.kenshin.dao.model;

import java.util.HashMap;
import java.util.Map;

import com.gtt.kenshin.dao.annotation.DaoActionType;

/**
 * DAO方法
 * 
 * @author tiantian.gao
 * @date 2011-5-24
 * 
 */
public class DaoMethod {

	private final String name;// 方法名称

	public final DaoActionType actionType;// 方法类型

	private Map<String, Object> params = new HashMap<String, Object>();// SQL语句参数

	public DaoMethod(String name, DaoActionType actionType) {
		this.name = name;
		this.actionType = actionType;
	}

	public String getName() {
		return name;
	}

	public void addParam(String name, Object value) {
		this.params.put(name, value);
	}

	public void addParams(Map<String, Object> params) {
		if (params != null) {
			this.params.putAll(params);
		}
	}

	public Map<String, Object> getParams() {
		return params;
	}

	// private CallResultType resultTypeIfCall;//存储过程的调用结果类型
	// private boolean isCallResult;// 是否声明了存储过程的调用结果类型
	//
	// public CallResultType getResultTypeIfCall() {
	// return resultTypeIfCall;
	// }
	//
	// public void setResultTypeIfCall(CallResultType resultTypeIfCall) {
	// this.resultTypeIfCall = resultTypeIfCall;
	// }
	//
	// public boolean isCallResult() {
	// return isCallResult;
	// }
	//
	// public void setCallResult(boolean isCallResult) {
	// this.isCallResult = isCallResult;
	// }

}
