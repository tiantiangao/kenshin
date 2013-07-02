package com.gtt.kenshin.dao;

import java.lang.annotation.Annotation;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.IntroductionInterceptor;

import com.gtt.kenshin.dao.annotation.DaoAction;
import com.gtt.kenshin.dao.model.DaoMethod;
import com.gtt.kenshin.dao.util.DaoUtils;

/**
 * DAO动态代理拦截器
 * 
 * @author tiantian.gao
 * @date 2011-5-24
 * 
 */
public class DaoAutoRealizer implements IntroductionInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {

		// DAO不是继承于GenericDao，不处理
		if (!implementsInterface(invocation.getMethod().getDeclaringClass())) {
			return invocation.proceed();
		}

		// 判断方法是否声明了DaoAction
		checkAnnotation(invocation);

		// 根据注解创建DaoMethod
		DaoMethod daoMethod = DaoUtils.createDaoMethod(invocation.getMethod(), invocation.getArguments());

		// 获取真实实现类，执行相应操作
		final GenericDao genericDao = (GenericDao) invocation.getThis();

		switch (daoMethod.actionType) {
		case LOAD:
			return genericDao.executeLoad(daoMethod);
		case QUERY:
			return genericDao.executeQuery(daoMethod);
		case LIMIT:
			return genericDao.executeLimit(daoMethod);
		case PAGE:
			return genericDao.executePageQuery(daoMethod);
		case INSERT:
			return genericDao.executeInsert(daoMethod);
		case UPDATE:
			return genericDao.executeUpdate(daoMethod);
		case DELETE:
			return genericDao.executeDelete(daoMethod);
		default:
			return invocation.proceed();
		}

		// if (daoMethod.actionType == DaoActionType.CALL) {
		// return genericDao.executeCall(daoMethod);
		// }

	}

	/**
	 * 检查方法是否声明了@DaoAction注解
	 * 
	 * @param invocation
	 * @throws Exception
	 */
	private void checkAnnotation(MethodInvocation invocation) throws Exception {
		Annotation daoAction = invocation.getMethod().getAnnotation(DaoAction.class);

		String methodName = invocation.getMethod().getName();

		if (daoAction == null) {
			throw new Exception("你必须用@DaoAction声明方法" + methodName);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean implementsInterface(Class clazz) {
		return clazz.isInterface() && GenericDao.class.isAssignableFrom(clazz);
	}

}
