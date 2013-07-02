package com.gtt.accounting.persistence.enums;

import com.gtt.accounting.persistence.enums.base.BaseStringEnum;

/**
 * 时间排序方式
 * 
 * @author tiantian.gao
 * @date 2011-7-4
 * 
 */
public enum TimeOrder implements BaseStringEnum<TimeOrder> {
	CREATED_ASC("created asc"), // 创建时间正序
	CREATED_DESC("created desc"), // 创建时间倒序
	LASTMODIFIED_ASC("lastModified asc"), // 最后修改时间正序
	LASTMODIFIED_DESC("lastModified desc");// 最后修改时间倒序

	private String value;

	private TimeOrder(String value) {
		this.value = value;
	}

	@Override
	public String getCode() {
		return this.value;
	}

	@Override
	public TimeOrder fromCode(String code) {
		for (TimeOrder order : values()) {
			if (order.value.equals(code)) {
				return order;
			}
		}
		return null;
	}

}
