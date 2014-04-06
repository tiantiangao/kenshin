package com.gtt.dao.test;

import com.gtt.kenshin.dao.GenericDao;
import com.gtt.kenshin.dao.annotation.DaoAction;
import com.gtt.kenshin.dao.annotation.DaoActionType;
import com.gtt.kenshin.dao.annotation.DaoParam;
import com.gtt.kenshin.dao.model.PageModel;

import java.util.List;

/**
 * TestDataDao
 *
 * @author tiantian.gao
 * @date 2011-7-1
 */
public interface TestDataDao extends GenericDao {

	@DaoAction(action = DaoActionType.QUERY)
	List<TestData> findAll();

	@DaoAction(action = DaoActionType.PAGE)
	PageModel<TestData> page(@DaoParam("page") int page, @DaoParam("max") int max);
}
