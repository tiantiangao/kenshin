package com.gtt.dao;

import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * 测试基类(带事务)
 * 
 * @author tiantian.gao
 * @date 2011-7-1
 * 
 */
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public abstract class AbstractTestWithTranx extends AbstractTest {

}
