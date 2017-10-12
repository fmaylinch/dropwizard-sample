package com.codethen.dropwizard.sample.database;

import com.codethen.dropwizard.sample.model.Book;
import com.codethen.dropwizard.sample.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Connects to the database using JDBC
 */
public class BookDaoJdbc implements BookDao {

	@Override
	public Book getById(int id) {

		List<Book> books = getBooksFromDB("select * from books where id = " + id);

		if (books.size() > 0) {
			return books.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Book> getAll() {

		return getBooksFromDB("select * from books");
	}

	@Override
	public List<Book> findByTitle(String titlePart) {

		return getBooksFromDB("select * from books where title like '%" + titlePart + "%'");
	}

	@Override
	public Book create(Book book) {
		return null;
	}

	@Override
	public Book update(Book book) {
		return null;
	}

	@Override
	public void delete(int id) {

	}



	/**
	 * From a SQL query, returns a list of books.
	 * If there are no result, returns an empty list.
	 */
	private List<Book> getBooksFromDB(String sql) {

		List<Book> result = new ArrayList<>();

		try {

			Connection conn = DbUtil.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				// Extract data from current row
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				int numPages = rs.getInt("num_pages");
				Date releaseDate = rs.getDate("release_date");

				// Add book to list
				Book book = new Book(id, title, author, numPages, releaseDate);
				result.add(book);
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {

			throw new RuntimeException("there was a problem with the database", e);
		}

		return result;
	}

}
