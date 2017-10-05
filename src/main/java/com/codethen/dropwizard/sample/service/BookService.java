package com.codethen.dropwizard.sample.service;

import com.codethen.dropwizard.sample.model.Book;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BookService {

	private Map<Integer, Book> books;

	private int nextId;

	public BookService() {

		books = new HashMap<>();
		books.put(1,  new Book(1,  "Head first Java", "Kathy Sierra, Bert Bates", 720, new Date()) );
		books.put(3,  new Book(3,  "Refactoring", "Martin Fowler", 464, new Date()) );
		books.put(4,  new Book(4,  "Head first design patterns", "Eric Freeman, Beth Robson", 694, new Date()) );
		books.put(5,  new Book(5, "Clean code", "Robert C. Martin", 288, new Date()) );

		nextId = 6; // This would be managed by the database
	}

	public Book getById(int id) {

		return books.get(id);
	}

	public Collection<Book> findByTitle(String search) {

		final Collection<Book> result;

		if (search != null) {
			result = this.books.values().stream()
				.filter(book -> book.getTitle().toLowerCase().contains(search.toLowerCase()))
				.collect(Collectors.toList());
		} else {
			result = this.books.values();
		}

		return result;
	}

	public Book addBook(Book book) {

		book.setId(nextId);

		books.put(nextId, book);

		nextId++;

		return book;
	}
}
