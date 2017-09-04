package com.codethen.dropwizard.sample.controller;

import com.codethen.dropwizard.sample.model.Book;
import com.codethen.dropwizard.sample.service.BookService;
import com.codethen.dropwizard.sample.util.HandlebarsUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Path("/books")
@Produces(MediaType.TEXT_HTML)
public class BookController {

	private BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GET
	public String viewBooks(@QueryParam("search") String search) {

		final Collection<Book> booksToDisplay = bookService.findByTitle(search);

		final Map<String, Object> values = new HashMap<>();
		values.put("books", booksToDisplay);

		return HandlebarsUtil.processTemplate("books", values);
	}

	@GET
	@Path("{id}")
	public String viewBook(@PathParam("id") int id) {

		final Book book = bookService.getById(id);

		final Map<String, Object> values = new HashMap<>();
		values.put("book", book);

		return HandlebarsUtil.processTemplate("book", values);
	}
}
