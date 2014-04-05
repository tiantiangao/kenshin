package com.gtt.dao;

import com.google.common.base.Splitter;
import com.gtt.kenshin.log.KenshinLogger;
import com.gtt.kenshin.log.KenshinLoggerFactory;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author tiantiangao
 */
public class HsqlDBInit {

	private static final KenshinLogger LOGGER = KenshinLoggerFactory.getLogger(HsqlDBInit.class);

	public void init() {
		try {
			Class.forName("org.hsqldb.jdbcDriver");

			Properties properties = new Properties();
			properties.load(new ClassPathResource("/config/db/conf.properties").getInputStream());
			String dbNames = properties.getProperty("db");

			Iterable<String> dbs = Splitter.on(",").split(dbNames);

			for (String db : dbs) {
				//在内存中建立数据库{db},用户名为sa,密码为空
				Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:" + db, "sa", "");
//				conn.setAutoCommit(false);

				String sql = IOUtils.toString(new ClassPathResource("/config/db/" + db + ".sql").getInputStream());
				Iterable<String> sqlList= Splitter.on(";").split(sql);

				Statement statement = conn.createStatement();
				for (String sqltemp : sqlList) {
//					statement.addBatch(sqltemp);
					statement.execute(sqltemp);
				}
//				statement.executeBatch();
//				conn.commit();
			}
		} catch (Exception e) {
			LOGGER.error("init: ", e);
		}
	}

}
