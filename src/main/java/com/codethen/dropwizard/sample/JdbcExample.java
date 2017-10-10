package com.codethen.dropwizard.sample;

import java.sql.*;

public class JdbcExample {

	public static void main(String[] args) throws SQLException {

		String host = "localhost";
		String schema = "books";
		String user = "root";
		String pwd = "YOUR PASSWORD HERE"; // TODO

		// Get a connection to the database
		Connection conn = DriverManager.getConnection(
			"jdbc:mysql://" + host + "/" + schema + "?user=" + user + "&password=" + pwd);

		// Create a statement
		Statement stmt = conn.createStatement();

		// Execute a SELECT query and get the results
		ResultSet rs = stmt.executeQuery("select * from books");

		System.out.println("Books found in the database:");

		// Loop through the results
		while (rs.next()) {

			// Each iteration is a row; get the values from the columns
			int id = rs.getInt("id");
			String title = rs.getString("title");
			String author = rs.getString("author");
			int numPages = rs.getInt("num_pages");
			Date releaseDate = rs.getDate("release_date");

			System.out.println(id + ", " + title + ", " + author + ", " + numPages + ", " + releaseDate);
		}

		// Close database objects to free resources
		rs.close();
		stmt.close();
		conn.close();
	}
}
