package com.codethen.dropwizard.sample.controller;

import com.codethen.dropwizard.sample.model.Book;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookApi {

	private Map<Integer, Book> books;

	public BookApi() {

		books = new HashMap<>();
		books.put(3,  new Book(3,  "Head first Java", "Kathy Sierra, Bert Bates", 720) );
		books.put(7,  new Book(7,  "Refactoring", "Martin Fowler", 464) );
		books.put(9,  new Book(9,  "Head first design patterns", "Eric Freeman, Beth Robson", 694) );
		books.put(12, new Book(12, "Clean code", "Robert C. Martin", 288) );
	}

	@GET
	public Collection<Book> viewBooks(@QueryParam("search") String search) {

		final Collection<Book> booksToDisplay;
		if (search != null) {
			booksToDisplay = books.values().stream()
				.filter(book -> book.getTitle().toLowerCase().contains(search.toLowerCase()))
				.collect(Collectors.toList());
		} else {
			booksToDisplay = books.values();
		}

		return booksToDisplay;
	}

	@GET
	@Path("{id}")
	public Book viewBook(@PathParam("id") int id) {

		return books.get(id);
	}
}
