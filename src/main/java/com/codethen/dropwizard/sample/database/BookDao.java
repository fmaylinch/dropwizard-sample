package com.codethen.dropwizard.sample.database;

import com.codethen.dropwizard.sample.model.Book;

import java.util.List;

/**
 * Manages the books database
 */
public interface BookDao extends GenericDao<Book, Integer> {

	/** Looks for books where the title contains the titlePart */
	List<Book> findByTitle(String titlePart);
}
