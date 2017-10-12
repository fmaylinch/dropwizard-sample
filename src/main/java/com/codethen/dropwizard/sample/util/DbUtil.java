package com.codethen.dropwizard.sample.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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


}
