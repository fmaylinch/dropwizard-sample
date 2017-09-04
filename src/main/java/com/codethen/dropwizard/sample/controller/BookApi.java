package com.codethen.dropwizard.sample.controller;

import com.codethen.dropwizard.sample.model.Book;
import com.codethen.dropwizard.sample.service.BookService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookApi {

	private BookService bookService;

	public BookApi(BookService bookService) {
		this.bookService = bookService;
	}

	@GET
	public Collection<Book> viewBooks(@QueryParam("search") String search) {

		return bookService.findByTitle(search);
	}

	@GET
	@Path("{id}")
	public Book viewBook(@PathParam("id") int id) {

		return bookService.getById(id);
	}
}
