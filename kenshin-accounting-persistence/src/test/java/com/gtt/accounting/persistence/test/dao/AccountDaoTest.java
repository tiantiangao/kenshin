package com.gtt.accounting.persistence.test.dao;

import java.util.List;

import com.gtt.accounting.AbstractTestWithTranx;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gtt.accounting.persistence.dao.AccountDao;
import com.gtt.accounting.persistence.entity.Account;
import com.gtt.accounting.persistence.enums.TimeOrder;
import com.gtt.kenshin.dao.model.PageModel;

/**
 * AccountDaoTest
 * 
 * @author tiantian.gao
 * 
 */
public class AccountDaoTest extends AbstractTestWithTranx {

	@Autowired
	private AccountDao accountDao;

	@Test
	public void test() {
		notNull(accountDao);
		long userId = 1;

		// find
		List<Account> accountList = accountDao.findByUser(userId, TimeOrder.CREATED_ASC.getCode());
		notNull(accountList);
		int oldSize = accountList.size();

		// add
		float price = 11;
		String memo = "测试备注";
		long id = accountDao.insert(userId, price, memo);
		isFalse(id == 0);

		// load
		Account account = accountDao.load(id);
		notNull(account);
		equals(userId, account.getUserId());
		equals(price, account.getPrice());
		equals(memo, account.getMemo());

		// find all
		accountList = accountDao.findByUser(userId, TimeOrder.CREATED_ASC.getCode());
		notNull(accountList);
		equals(oldSize + 1, accountList.size());

		// page
		PageModel model = accountDao.paginateByUser(userId, TimeOrder.CREATED_ASC.getCode(), 1, 20);
		notNull(model);
		notNull(model.getRecords());

		// update
		price = -100;
		memo = "买东东";
		accountDao.update(id, price, memo);

		account = accountDao.load(id);
		notNull(account);
		equals(userId, account.getUserId());
		equals(price, account.getPrice());
		equals(memo, account.getMemo());
	}
}
