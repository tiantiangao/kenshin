package com.gtt.kenshin.dao.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.gtt.dao.test.TestData;
import com.gtt.dao.test.TestDataDao;
import com.gtt.kenshin.test.base.AbstractTestWithTranx;

public class TestDataTest extends AbstractTestWithTranx {

	@Autowired
	private TestDataDao testDataDao;

	@Test
	public void test() {
		System.out.println("测试开始");

		Assert.notNull(testDataDao);

		List<TestData> all = testDataDao.findAll();

		System.out.println("总数量：" + all.size());
		System.out.println("测试结束");
	}
}
