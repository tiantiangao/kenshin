package com.gtt.dao;

import junit.framework.Assert;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试基类
 * 
 * @author tiantian.gao
 * @date 2011-7-1
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/config/spring/appcontext-*.xml" })
public abstract class AbstractTest {

	protected void notNull(Object object) {
		Assert.assertNotNull(object);
	}

	protected void isNull(Object object) {
		Assert.assertNull(object);
	}

	protected void equals(Object expected, Object actual) {
		Assert.assertEquals(expected, actual);
	}

	protected void isTrue(boolean condition) {
		Assert.assertTrue(condition);
	}

	protected void isFalse(boolean condition) {
		Assert.assertFalse(condition);
	}

}
