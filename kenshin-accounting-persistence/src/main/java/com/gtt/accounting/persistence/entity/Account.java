package com.gtt.accounting.persistence.entity;

import com.gtt.kenshin.dao.entity.BaseEntity;

/**
 * 账目实体
 * 
 * @author tiantian.gao
 * @date 2011-7-4
 * 
 */
public class Account extends BaseEntity {

	private long userId; // 用户ID

	private float price;// 账目金额

	private String memo;// 账目说明

	// getter/setter

	public String getMemo() {
		return memo;
	}

	public float getPrice() {
		return price;
	}

	public long getUserId() {
		return userId;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
