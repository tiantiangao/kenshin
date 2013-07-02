package com.gtt.kenshin.dao.entity;

import java.util.Date;

/**
 * 实体对象基类
 * 
 * @author tiantian.gao
 * @date 2011-7-4
 * 
 */
public abstract class BaseEntity {

	private long id;

	private Date created;

	private Date lastModified;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

}
