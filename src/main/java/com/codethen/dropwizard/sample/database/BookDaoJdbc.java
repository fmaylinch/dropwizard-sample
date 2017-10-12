package com.codethen.dropwizard.sample.database;

import com.codethen.dropwizard.sample.model.Book;
import com.codethen.dropwizard.sample.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Connects to the database using JDBC
 */
public class BookDaoJdbc implements BookDao {

	@Override
	public Book getById(int id) {

		Function<Connection, PreparedStatement> function = conn -> {
			try {
				PreparedStatement ps = conn.prepareStatement("select * from books where id = ?");
				ps.setInt(1, id);
				return ps;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		};

		List<Book> books = getBooksFromDB(function);

		if (books.size() > 0) {
			return books.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Book> getAll() {

		Function<Connection, PreparedStatement> function = conn -> {
			try {
				return conn.prepareStatement("select * from books");
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		};

		return getBooksFromDB(function);
	}

	@Override
	public List<Book> findByTitle(String titlePart) {

		Function<Connection, PreparedStatement> function = conn -> {
			try {
				PreparedStatement ps = conn.prepareStatement("select * from books where title like ?");
				ps.setString(1, "%" + titlePart + "%");
				return ps;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		};

		return getBooksFromDB(function);
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
	private List<Book> getBooksFromDB(Function<Connection, PreparedStatement> function) {

		List<Book> result = new ArrayList<>();

		try {

			Connection conn = DbUtil.getConnection();

			// Execute the function that was passed by parameter
			PreparedStatement ps = function.apply(conn);

			ResultSet rs = ps.executeQuery();

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
			ps.close();
			conn.close();

		} catch (SQLException e) {

			throw new RuntimeException("there was a problem with the database", e);
		}

		return result;
	}

}
