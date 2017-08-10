package com.codethen.dropwizard.sample.resources;

import com.codethen.dropwizard.sample.model.Book;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Path("/books")
@Produces(MediaType.TEXT_HTML)
public class BookResource {

	private Map<Integer, Book> books;

	public BookResource() {

		books = new HashMap<>();
		books.put(3,  new Book(3,  "Head first Java", "Kathy Sierra, Bert Bates", 720) );
		books.put(7,  new Book(7,  "Refactoring", "Martin Fowler", 464) );
		books.put(9,  new Book(9,  "Head first design patterns", "Eric Freeman, Beth Robson", 694) );
		books.put(12, new Book(12, "Clean code", "Robert C. Martin", 288) );
	}

	@GET
	public String viewBooks() throws IOException {

		MustacheFactory mf = new DefaultMustacheFactory();
		Mustache mustache = mf.compile("books.html");
		StringWriter writer = new StringWriter();
		mustache.execute(writer, books.values()).flush();
		return writer.toString();
	}

	@GET
	@Path("{id}")
	public String viewBook(@PathParam("id") int id) throws IOException {

		Book book = books.get(id);

		if (book != null) {

			MustacheFactory mf = new DefaultMustacheFactory();
			Mustache mustache = mf.compile("book.html");
			StringWriter writer = new StringWriter();
			mustache.execute(writer, book).flush();
			return writer.toString();

		} else {
			return "Book with id " + id + " not found!";
		}
	}
}
