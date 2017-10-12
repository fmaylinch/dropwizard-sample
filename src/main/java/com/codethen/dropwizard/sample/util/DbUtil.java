package com.codethen.dropwizard.sample.util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Util methods for using the database
 */
public class DbUtil {

	public static Connection getConnection() throws SQLException
	{
		String host = "localhost";
		String schema = "books";
		String user = "root";
		String pwd = "maysicuel";

		return DriverManager.getConnection(
			"jdbc:mysql://" + host + "/" + schema + "?user=" + user + "&password=" + pwd);
	}

	public static DataSource getDataSource() throws SQLException {

		String host = "localhost";
		String schema = "books";
		String user = "root";
		String pwd = "maysicuel";

		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setURL("jdbc:mysql://" + host + "/" + schema);
		dataSource.setUser(user);
		dataSource.setPassword(pwd);

		return dataSource;
	}
}
