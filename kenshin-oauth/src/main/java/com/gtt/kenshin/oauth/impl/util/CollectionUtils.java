package com.gtt.kenshin.oauth.impl.util;

import java.util.List;

/**
 * @author tiantiangao
 */
public class CollectionUtils {

	public static boolean isEmpty(List<? extends Object> providerList) {
		return providerList == null || providerList.isEmpty();
	}
}
