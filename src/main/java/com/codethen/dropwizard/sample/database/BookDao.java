package com.codethen.dropwizard.sample.database;

import com.codethen.dropwizard.sample.model.Book;

import java.util.List;

/**
 * Manages the books database
 */
public interface BookDao {

	/** Returns a books by id, or null if doesn't exist */
	Book getById(int id);

	List<Book> getAll();

	/** Looks for books where the title contains the titlePart */
	List<Book> findByTitle(String titlePart);

	Book create(Book book);

	Book update(Book book);

	void delete(int id);
}
