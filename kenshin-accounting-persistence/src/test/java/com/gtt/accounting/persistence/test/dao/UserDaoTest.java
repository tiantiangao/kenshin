package com.gtt.accounting.persistence.test.dao;

import com.gtt.accounting.AbstractTestWithTranx;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gtt.accounting.persistence.dao.UserDao;
import com.gtt.accounting.persistence.entity.User;

/**
 * UserDaoTest
 * 
 * @author tiantian.gao
 * @date 2011-7-4
 * 
 */
public class UserDaoTest extends AbstractTestWithTranx {

	@Autowired
	private UserDao userDao;

	@Test
	public void test() {
		notNull(userDao);

		// add
		String username = "markao";
		String password = "123456";
		String email = "gao12581@sina.com";
		long userId = userDao.insert(username, password, email);
		isFalse(userId == 0);

		// load by name
		User user = userDao.loadByUsername(username);
		notNull(user);
		equals(username, user.getUsername());
		equals(password, user.getPassword());
		equals(email, user.getEmail());
		isTrue(user.getStatus() == 1);

		// load by id
		user = userDao.loadById(userId);
		notNull(user);
		equals(username, user.getUsername());
		equals(password, user.getPassword());
		equals(email, user.getEmail());
		isTrue(user.getStatus() == 1);

		// update
		password = "345678";
		email = "gao12581@sina.cn";
		userDao.update(userId, password, email, true);

		// load by id
		user = userDao.loadById(userId);
		notNull(user);
		equals(username, user.getUsername());
		equals(password, user.getPassword());
		equals(email, user.getEmail());
		isTrue(user.getStatus() == 1);

		// delete by id
		userDao.delete(userId);

		// load by id
		user = userDao.loadById(userId);
		isNull(user);

	}
}
