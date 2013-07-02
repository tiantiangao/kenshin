package com.gtt.dao.test;

import java.util.List;

import com.gtt.kenshin.dao.GenericDao;
import com.gtt.kenshin.dao.annotation.DaoAction;
import com.gtt.kenshin.dao.annotation.DaoActionType;

/**
 * TestDataDao
 * 
 * @author tiantian.gao
 * @date 2011-7-1
 * 
 */
public interface TestDataDao extends GenericDao {

	@DaoAction(action = DaoActionType.QUERY)
	List<TestData> findAll();
}
