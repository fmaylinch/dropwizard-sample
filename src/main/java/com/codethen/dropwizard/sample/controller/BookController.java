package com.codethen.dropwizard.sample.controller;

import com.codethen.dropwizard.sample.model.Book;
import com.codethen.dropwizard.sample.util.HandlebarsUtil;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Path("/books")
@Produces(MediaType.TEXT_HTML)
public class BookController {

	private Map<Integer, Book> books;

	public BookController() {

		books = new HashMap<>();
		books.put(3,  new Book(3,  "Head first Java", "Kathy Sierra, Bert Bates", 720) );
		books.put(7,  new Book(7,  "Refactoring", "Martin Fowler", 464) );
		books.put(9,  new Book(9,  "Head first design patterns", "Eric Freeman, Beth Robson", 694) );
		books.put(12, new Book(12, "Clean code", "Robert C. Martin", 288) );
	}

	@GET
	public String viewBooks() {

		final Map<String, Object> values = new HashMap<>();
		values.put("books", books.values());

		return HandlebarsUtil.processTemplate("books", values);
	}

	@GET
	@Path("{id}")
	public String viewBook(@PathParam("id") int id) {

		final Book book = books.get(id);

		final Map<String, Object> values = new HashMap<>();
		values.put("book", book);

		return HandlebarsUtil.processTemplate("book", values);
	}
}
