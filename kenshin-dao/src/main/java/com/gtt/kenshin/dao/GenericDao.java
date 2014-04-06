package com.gtt.kenshin.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.gtt.kenshin.dao.model.DaoMethod;
import com.gtt.kenshin.dao.model.PageModel;

/**
 * DAO基类
 * 
 * @author tiantian.gao
 * @date 2011-5-24
 * 
 */
public interface GenericDao {

	/**
	 * Execute query return multiple records
	 */
	List<Object> executeQuery(DaoMethod DaoMethod) throws DataAccessException;

	/**
	 * Execute query for multiple page
	 */
	<T> PageModel<T> executePageQuery(DaoMethod DaoMethod) throws DataAccessException;

	/**
	 * Execute query only return one record,if multiple records are exists,
	 * throw a {@link DataAccessException}
	 */
	Object executeLoad(DaoMethod DaoMethod) throws DataAccessException;

	/**
	 * @param method
	 * @param args
	 * @return
	 * @throws DataAccessException
	 */
	Object executeInsert(DaoMethod DaoMethod) throws DataAccessException;

	/**
	 * @param method
	 * @param args
	 * @throws DataAccessException
	 */
	int executeUpdate(DaoMethod DaoMethod) throws DataAccessException;

	/**
	 * @param method
	 * @param args
	 * @throws DataAccessException
	 */
	int executeDelete(DaoMethod DaoMethod) throws DataAccessException;

	// /**
	// * Execute the stored procedure
	// */
	// Object executeCall(DaoMethod DaoMethod) throws DataAccessException;

	/**
	 * Execute query for limited results
	 * 
	 * @param DaoMethod
	 * @return
	 */
	Object executeLimit(DaoMethod DaoMethod);
}
