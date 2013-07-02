package com.gtt.accounting.persistence.dao;

import java.util.List;

import com.gtt.accounting.persistence.entity.Account;
import com.gtt.kenshin.dao.GenericDao;
import com.gtt.kenshin.dao.annotation.DaoAction;
import com.gtt.kenshin.dao.annotation.DaoActionType;
import com.gtt.kenshin.dao.annotation.DaoParam;
import com.gtt.kenshin.dao.model.PageModel;

/**
 * 账目DAO
 * 
 * @author tiantian.gao
 * @date 2011-7-4
 * 
 */
public interface AccountDao extends GenericDao {

	@DaoAction(action = DaoActionType.INSERT)
	long insert(@DaoParam("userId") long userId, @DaoParam("price") float price, @DaoParam("memo") String memo);

	@DaoAction(action = DaoActionType.LOAD)
	Account load(@DaoParam("id") long id);

	@DaoAction(action = DaoActionType.QUERY)
	List<Account> findByUser(@DaoParam("userId") long userId, @DaoParam("orderBy") String orderBy);

	@DaoAction(action = DaoActionType.PAGE)
	PageModel paginateByUser(@DaoParam("userId") long userId, @DaoParam("orderBy") String orderBy,
			@DaoParam("page") int page, @DaoParam("max") int max);

	@DaoAction(action=DaoActionType.UPDATE)
	void update(@DaoParam("id") long id, @DaoParam("price") float price, @DaoParam("memo") String memo);
}
