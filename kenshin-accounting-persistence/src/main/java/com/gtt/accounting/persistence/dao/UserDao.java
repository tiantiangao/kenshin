package com.gtt.accounting.persistence.dao;

import com.gtt.accounting.persistence.entity.User;
import com.gtt.kenshin.dao.GenericDao;
import com.gtt.kenshin.dao.annotation.DaoAction;
import com.gtt.kenshin.dao.annotation.DaoActionType;
import com.gtt.kenshin.dao.annotation.DaoParam;

/**
 * 用户DAO
 * 
 * @author tiantian.gao
 * @date 2011-7-4
 * 
 */
public interface UserDao extends GenericDao {

	/**
	 * 新增用户
	 * 
	 * @param username
	 * @param password
	 * @param email
	 * @return
	 */
	@DaoAction(action = DaoActionType.INSERT)
	long insert(@DaoParam("username") String username, @DaoParam("password") String password,
			@DaoParam("email") String email);

	/**
	 * 通过ID获取用户
	 * 
	 * @param id
	 * @return
	 */
	@DaoAction(action = DaoActionType.LOAD)
	User loadById(@DaoParam("id") long id);

	/**
	 * 通过用户名获取用户
	 * 
	 * @param username
	 * @return
	 */
	@DaoAction(action = DaoActionType.LOAD)
	User loadByUsername(@DaoParam("username") String username);

	/**
	 * 更新用户
	 * 
	 * @param id
	 * @param password
	 * @param email
	 * @param status
	 */
	@DaoAction(action = DaoActionType.UPDATE)
	void update(@DaoParam("id") long id, @DaoParam("password") String password, @DaoParam("email") String email,
			@DaoParam("status") boolean status);

	/**
	 * 删除用户
	 * 
	 * @param id
	 */
	@DaoAction(action = DaoActionType.UPDATE)
	void delete(@DaoParam("id") long id);

}
