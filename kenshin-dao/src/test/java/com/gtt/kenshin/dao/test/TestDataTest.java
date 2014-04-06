package com.gtt.kenshin.dao.test;

import com.gtt.dao.AbstractTestWithTranx;
import com.gtt.dao.test.TestData;
import com.gtt.dao.test.TestDataDao;
import com.gtt.kenshin.dao.model.PageModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestDataTest extends AbstractTestWithTranx {

	@Autowired
	private TestDataDao testDataDao;

	@Test
	public void test() {
		assertThat(testDataDao).isNotNull();

		List<TestData> all = testDataDao.findAll();

		assertThat(all).isNotNull();
		assertThat(all.size()).isEqualTo(1);

		PageModel<TestData> model = testDataDao.page(1, 20);
		assertThat(model).isNotNull();
		assertThat(model.getRecordCount()).isEqualTo(1);
		assertThat(model.getRecords().size()).isEqualTo(1);
		assertThat(model.getRecords().get(0).getId()).isEqualTo(1);
	}

}
