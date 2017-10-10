package com.codethen.dropwizard.sample;

import java.sql.*;

public class JdbcExample {

	public static void main(String[] args) throws SQLException {

		String host = "localhost";
		String schema = "books";
		String user = "root";
		String pwd = "YOUR PASSWORD HERE"; // TODO

		Connection conn = DriverManager.getConnection(
			"jdbc:mysql://" + host + "/" + schema + "?user=" + user + "&password=" + pwd);


		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from books");

		while (rs.next()) {

			int id = rs.getInt("id");
			String title = rs.getString("title");
			String author = rs.getString("author");
			int numPages = rs.getInt("num_pages");
			Date releaseDate = rs.getDate("release_date");

			System.out.println(id + ", " + title + ", " + author);
		}

		rs.close();
		stmt.close();
		conn.close();
	}
}
