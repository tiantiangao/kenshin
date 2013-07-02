package com.gtt.accounting.persistence.enums;

import com.gtt.accounting.persistence.enums.base.BaseStringEnum;

/**
 * 账目顺序
 * 
 * @author tiantian.gao
 * @date 2011-7-4
 * 
 */
public enum AccountOrder implements BaseStringEnum<AccountOrder> {
	;

	private String value;

	private AccountOrder(String value) {
		this.value = value;
	}

	@Override
	public String getCode() {
		return this.value;
	}

	@Override
	public AccountOrder fromCode(String code) {
		for (AccountOrder order : values()) {
			if (order.value.equals(code)) {
				return order;
			}
		}
		return null;
	}

}
