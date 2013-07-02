package com.gtt.kenshin.dao.ibatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.gtt.kenshin.dao.GenericDao;
import com.gtt.kenshin.dao.model.DaoMethod;
import com.gtt.kenshin.dao.model.PageModel;
import com.gtt.kenshin.exception.SystemException;

/**
 * {@link GenericDao}的iBatis实现，所有的调用都会被代理给SqlMapClientDaoSupport. <br>
 * SQL语句根据命名空间和 {@link DaoMethod#getName()}来查找.
 * 
 * @author tiantian.gao
 * @date 2011-7-1
 * 
 */
public class IBatisGenericDaoImpl extends SqlMapClientDaoSupport implements GenericDao {

	/**
	 * iBatis name space
	 */
	private final String namespace;

	/**
	 * Constructor with name space
	 */
	public IBatisGenericDaoImpl(String namespace) {
		this.namespace = namespace;
	}

	@Override
	public Object executeInsert(DaoMethod daoMethod) throws DataAccessException {
		String statementName = getStatementName(daoMethod);
		return this.getSqlMapClientTemplate().insert(statementName, daoMethod.getParams());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> executeQuery(DaoMethod DaoMethod) throws DataAccessException {
		String statementName = getStatementName(DaoMethod);
		return this.getSqlMapClientTemplate().queryForList(statementName, DaoMethod.getParams());
	}

	@Override
	public Object executeLimit(DaoMethod DaoMethod) {
		Map<String, Object> daoParams = DaoMethod.getParams();
		Integer skip = (Integer) daoParams.get(IBatisPageConstants.PAGE_SKIP);
		Integer max = (Integer) daoParams.get(IBatisPageConstants.PAGE_MAX);
		if (skip == null && max == null) {
			throw new SystemException("Must define skip or max parameter as least.");
		}
		return getSqlMapClientTemplate().queryForList(getStatementName(DaoMethod), daoParams, skip, max);
	}

	@Override
	public PageModel executePageQuery(DaoMethod DaoMethod) throws DataAccessException {

		PageModel pageModel = new PageModel();

		// count
		int recordCount = queryTotalCount(DaoMethod);

		if (recordCount > 0) {
			Map<String, Object> daoParams = DaoMethod.getParams();

			Integer max = (Integer) daoParams.get(IBatisPageConstants.PAGE_MAX);
			Integer pageNo = (Integer) daoParams.get(IBatisPageConstants.PAGE_NO);

			if (max == null || pageNo == null) {
				throw new SystemException("Must define skip or max parameter as least.");
			}

			int pageIndex = pageNo - 1;
			Integer skip = pageIndex >= 0 ? pageIndex * max : 0;
			daoParams.put(IBatisPageConstants.PAGE_SKIP, skip);

			// setting PageModel
			pageModel.setPage(pageNo);
			pageModel.setPageSize(max);
			pageModel.setRecordCount(recordCount);
			pageModel.setRecords((List<?>) executeLimit(DaoMethod));
		} else {
			pageModel.setRecords(new ArrayList<Object>());
		}

		return pageModel;
	}

	@Override
	public int executeDelete(DaoMethod DaoMethod) throws DataAccessException {
		String statementName = getStatementName(DaoMethod);
		return this.getSqlMapClientTemplate().delete(statementName, DaoMethod.getParams());
	}

	// /**
	// * Execute the stored procedure
	// */
	// @SuppressWarnings("unchecked")
	// @Override
	// public Object executeCall(DaoMethod DaoMethod) throws DataAccessException
	// {
	// List<Object> nestedResults = new ArrayList<Object>();
	// Object result = null;
	// int affectedIfUpdateCall = -1;
	//
	// String statementName = getStatementName(DaoMethod);
	// Map<String, Object> params = DaoMethod.getParams();
	// switch (DaoMethod.getResultTypeIfCall()) {
	// case Single:
	// result = getSqlMapClientTemplate().queryForObject(statementName, params);
	// nestedResults.add(result);
	// break;
	// case List:
	// result = getSqlMapClientTemplate().queryForList(statementName, params);
	// nestedResults.addAll((Collection<? extends Object>) result);
	// break;
	// case None:
	// affectedIfUpdateCall = getSqlMapClientTemplate().update(statementName,
	// params);
	// break;
	// default:
	// throw new UnsupportedOperationException("Not supported call result type["
	// + DaoMethod.getResultTypeIfCall()
	// + "].");
	// }
	//
	// if (!DaoMethod.isCallResult()) {
	// return result;
	// }
	// return new CallResult(nestedResults, params, affectedIfUpdateCall);
	// }

	@Override
	public int executeUpdate(DaoMethod DaoMethod) throws DataAccessException {
		String statementName = getStatementName(DaoMethod);
		return this.getSqlMapClientTemplate().update(statementName, DaoMethod.getParams());
	}

	@Override
	public Object executeLoad(DaoMethod DaoMethod) throws DataAccessException {
		String statementName = getStatementName(DaoMethod);
		return this.getSqlMapClientTemplate().queryForObject(statementName, DaoMethod.getParams());
	}

	/**
	 * 计算分页查询的总记录数
	 */
	private int queryTotalCount(DaoMethod DaoMethod) {
		String statementName = getCountStatementName(DaoMethod);

		Object ret = this.getSqlMapClientTemplate().queryForObject(statementName, DaoMethod.getParams());
		return ret == null ? 0 : (Integer) ret;

	}

	// private int autoCalcTotalCount(DaoMethod DaoMethod) {
	//
	// String statementName = getStatementName(DaoMethod);
	//
	// MappedStatement statment = ((ExtendedSqlMapClient)
	// (this.getSqlMapClient())).getMappedStatement(statementName);
	// Sql sql = statment.getSql();
	//
	// SessionScope sessionScope = new SessionScope();
	// sessionScope.setSqlMapClient(getSqlMapClient());
	// sessionScope.setSqlMapExecutor(getSqlMapClient());
	// sessionScope.setSqlMapTxMgr(getSqlMapClient());
	//
	// StatementScope statementScope = new StatementScope(sessionScope);
	//
	// String sqlText = sql.getSql(statementScope, DaoMethod.getParams());
	//
	// String countSql = SqlUtils.generateCountSql(sqlText);
	//
	// ParameterMap paramMap = sql.getParameterMap(statementScope,
	// DaoMethod.getParams());
	// Object[] paramValues = paramMap.getParameterObjectValues(statementScope,
	// DaoMethod.getParams());
	//
	// SqlMapClient sqlMapClient = this.getSqlMapClient();
	//
	// if (sqlMapClient instanceof ExtendedSqlMapClient) {
	// SqlMapExecutorDelegate delegate = ((ExtendedSqlMapClient)
	// sqlMapClient).getDelegate();
	//
	// Field field = ReflectionUtils.findField(SqlMapExecutorDelegate.class,
	// "sqlExecutor", SqlExecutor.class);
	//
	// Object v = ReflectionUtils.getField(field, delegate);
	//
	// if (v instanceof LimitSqlExecutor) {
	// SqlConverter sqlConvert = ((LimitSqlExecutor) v).getSqlConvert();
	// if (sqlConvert.canGenCountSql()) {
	// CountSqlStatement countSqlStatement =
	// sqlConvert.generateCountSql(countSql, paramValues);
	// processSql(countSqlStatement);
	// }
	// }
	// }
	//
	// return -1;
	// }

	/**
	 * Get statement name
	 */
	private String getStatementName(DaoMethod DaoMethod) {
		return namespace + "." + DaoMethod.getName();
	}

	/**
	 * Return count statement name
	 */
	private String getCountStatementName(DaoMethod DaoMethod) {
		return getStatementName(DaoMethod) + IBatisPageConstants.DEFAULT_SUFFIX;
	}

}
