package com.codethen.dropwizard.sample.service;

import com.codethen.dropwizard.sample.model.Book;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

public class BookService {

	public BookService() {

	}

	public Book getById(int id) {

		return null; // TODO books.get(id);
	}

	public Collection<Book> findByTitle(String search) {

		final Collection<Book> result;

		List<Book> books = getBooksFromDB();

		if (search != null) {
			result = books.stream()
				.filter(book -> book.getTitle().toLowerCase().contains(search.toLowerCase()))
				.collect(Collectors.toList());
		} else {
			result = books;
		}

		return result;
	}

	private List<Book> getBooksFromDB() {

		List<Book> result = new ArrayList<>();

		String host = "localhost";
		String schema = "books";
		String user = "root";
		String pwd = "maysicuel";


		try {

			// code that may fail
			Connection conn = DriverManager.getConnection(
				"jdbc:mysql://" + host + "/" + schema + "?user=" + user + "&password=" + pwd);

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from books");

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

		} catch(SQLException e) {

			// what to do in case of error???
			throw new RuntimeException("there was a problem with the database", e);
		}

		return result;
	}

	public Book addBook(Book book) {

		// TODO

		//book.setId(nextId);
		// books.put(nextId, book);
		//nextId++;
		return book;
	}
}
