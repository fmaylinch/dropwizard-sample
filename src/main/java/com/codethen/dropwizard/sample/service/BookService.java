package com.codethen.dropwizard.sample.service;

import com.codethen.dropwizard.sample.database.BookDao;
import com.codethen.dropwizard.sample.model.Book;

import java.util.List;

public class BookService {

	private BookDao bookDao;

	public BookService(BookDao bookDao) {
		this.bookDao = bookDao;
	}


	/** Returns a book by id, or null if not found. */
	public Book getById(int id) {

		return bookDao.getById(id);
	}

	public List<Book> getAll() {

		return bookDao.getAll();
	}

	public List<Book> findByTitle(String search) {

		return bookDao.findByTitle(search);
	}

	public Book add(Book book) {

		return bookDao.create(book);
	}

	public Book modify(Book book) {

		return bookDao.update(book);
	}

	/** Deletes the book, and returns true if it existed */
	public boolean delete(int id) {

		final Book book = getById(id);

		if (book != null) {
			bookDao.delete(id);
			return true;
		} else {
			return false;
		}
	}
}
