package com.gtt.kenshin.cache;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 缓存key
 * 
 * @author tiantiangao
 */
public class CacheKey {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -1099870460150967658L;
	/**
	 * Item category
	 */
	private String category;
	/**
	 * Parameters
	 */
	private Object[] params;

	/**
	 * Constructor
	 */
	public CacheKey(String category, Object... params) {
		this.category = category;
		this.params = params;
	}

	public String getCategory() {
		return category;
	}

	/**
	 * @return the params
	 */
	public Object[] getParams() {
		return params;
	}

	public List<Object> getParamsAsList() {
		if (params == null) {
			return Collections.emptyList();
		}
		return Arrays.asList(params);
	}

	@Override
	public String toString() {
		return "CacheKey[category:" + category + ", indexParams:" + ArrayUtils.toString(params) + "]";
	}

}
