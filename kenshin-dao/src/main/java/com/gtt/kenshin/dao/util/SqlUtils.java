package com.gtt.kenshin.dao.util;

/**
 * SQL语句工具类
 * 
 * @author tiantian.gao
 * @date 2011-7-1
 * 
 */
public class SqlUtils {

	/**
	 * Select syntax
	 */
	private static final String TOKEN_SELECT = "SELECT ";
	/**
	 * From syntax
	 */
	private static final String TOKEN_FROM = " FROM ";
	/**
	 * Group by syntax
	 */
	private static final String TOKEN_GROUP_BY = "GROUP BY ";
	/**
	 * Order by syntax
	 */
	private static final String TOKEN_ORDER_BY = "ORDER BY ";

	/**
	 * Desc by syntax
	 */
	private static final String TOKEN_DESC = " DESC";

	/**
	 * Asc by syntax
	 */
	private static final String TOKEN_ASC = " ASC";

	/**
	 * Generate count sql. Replace the selected fields as "count(*)" and
	 * eliminate "group by"
	 */
	public static String generateCountSql(final String sql) {
		String _sql = sql.trim().toUpperCase();

		if (_sql.endsWith(";")) {
			_sql = _sql.substring(0, _sql.length());
		}

		_sql = _sql.replaceAll(TOKEN_DESC, "");
		_sql = _sql.replaceAll(TOKEN_ASC, "");
		_sql = _sql.replaceAll("\r\n", "\\s");
		_sql = _sql.replaceAll("\r\n", "\\s");
		_sql = _sql.replaceAll("\\s+", "\\s");
		_sql = _sql.replaceAll("\\s+,\\s+", ",");

		StringBuffer countSql = new StringBuffer();

		int fromIndex = _sql.indexOf(TOKEN_FROM);

		countSql.append(TOKEN_SELECT);
		countSql.append("COUNT(*) AS _COUNT_");
		countSql.append(TOKEN_FROM);
		countSql.append(_sql.substring(fromIndex));

		_sql = countSql.toString();

		_sql = elininateToken(_sql, TOKEN_GROUP_BY);
		_sql = elininateToken(_sql, TOKEN_ORDER_BY);

		return _sql;
	}

	private static String elininateToken(final String sql, final String token) {
		String _sql = sql.trim();

		int tokenIndex = _sql.indexOf(token);

		if (tokenIndex == -1) {
			return _sql;
		}

		StringBuffer countSql = new StringBuffer();

		countSql.append(_sql.subSequence(0, tokenIndex));

		// eliminate group by statement
		_sql = _sql.substring(token.length()).trim();

		int continueIndex = -1;

		for (int i = 0; i < _sql.length(); i++) {
			char c = _sql.charAt(i);

			if (c == ' ') {
				continueIndex = i + 1;
				break;
			}
		}

		if (continueIndex != -1 && continueIndex < _sql.length()) {
			countSql.append(_sql.substring(continueIndex));
		}

		return _sql;
	}
}
