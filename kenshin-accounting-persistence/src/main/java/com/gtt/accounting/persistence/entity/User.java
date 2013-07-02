package com.gtt.accounting.persistence.entity;

import com.gtt.kenshin.dao.entity.BaseEntity;

/**
 * 用户实体
 * 
 * @author tiantian.gao
 * @date 2011-7-4
 * 
 */
public class User extends BaseEntity {

	private String username; // 用户名

	private String password;// 密码

	private String email;// 邮箱地址

	private int status;// 状态(0-已删除, 1-可用)

	// getter/setter

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public int getStatus() {
		return status;
	}

	public String getUsername() {
		return username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
