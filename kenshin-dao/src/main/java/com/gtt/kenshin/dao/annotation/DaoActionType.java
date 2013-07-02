package com.gtt.kenshin.dao.annotation;

/**
 * DAO方法类型
 * 
 * @author tiantian.gao
 * @date 2011-5-24
 * 
 */
public enum DaoActionType {

	/**
	 * load single record
	 */
	LOAD,
	/**
	 * Search multiple records
	 */
	QUERY,
	/**
	 * Add new action type but existent 'QUERY', for compatible with existent
	 * business system's code
	 */
	LIMIT,
	/**
	 * Multiple pages
	 */
	PAGE,
	/**
	 * Insert
	 */
	INSERT,
	/**
	 * Update
	 */
	UPDATE,
	/**
	 * Delete
	 */
	DELETE;

	// /**
	// * Call stored procedure
	// */
	// CALL;

}
