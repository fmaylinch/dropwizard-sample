package com.codethen.dropwizard.sample.service;

import com.codethen.dropwizard.sample.model.Book;
import com.codethen.dropwizard.sample.util.DbUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BookServiceOld {

	private SimpleDateFormat sqlDateFormat;

	public BookServiceOld() {
		sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}

	/*
	public Book getById(int id) {

		List<Book> books = getBooksFromDB("select * from books where id = " + id);

		return books.isEmpty() ? null : books.get(0);
		// return if (books.isEmpty()) null else books.get(0)
	}

	public Collection<Book> findByTitle(String search) {

		final Collection<Book> result;

		if (search != null) {
			result = getBooksFromDB("select * from books where title like '%" + search + "%'");
		} else {
			result = getBooksFromDB("select * from books");
		}

		return result;
	}

	public Book addBook(Book book) {

		insertBook(book);

		return book;
	}



	// --- Database access ---


	private void insertBook(Book book) {

		try {

			// Handle null date
			String dateStr =  book.getReleaseDate() != null ?
				"'" + sqlDateFormat.format(book.getReleaseDate()) + "'"
				: null;

			Connection conn = DbUtil.getConnection();
			Statement stmt = conn.createStatement();
			String sql = "insert into books (title, author, num_pages, release_date) values ('"
				+ book.getTitle() + "', '" + book.getAuthor() + "', "
				+ book.getNumPages() + ", " + dateStr + ")";

			System.out.println(sql);
			stmt.executeUpdate(sql);

			stmt.close();
			conn.close();

		} catch (SQLException e) {

			throw new RuntimeException("there was a problem with the database", e);
		}
	}
	*/
}
