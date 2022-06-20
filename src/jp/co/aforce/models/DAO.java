package jp.co.aforce.models;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAO {

	static DataSource ds;

	public Connection getConnection() throws Exception {

		if (ds == null) {
			InitialContext ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:/comp/env/jdbc/member_information");
		}

		return ds.getConnection();

	}

}
