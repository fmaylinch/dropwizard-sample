package com.codethen.dropwizard.sample.resources;

import com.codethen.dropwizard.sample.model.Book;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/books")
@Produces(MediaType.TEXT_HTML)
public class BookResource {

	private List<Book> books;

	public BookResource() {

		books = new ArrayList<>();
		books.add( new Book(1, "Head first Java", "Kathy Sierra, Bert Bates", 720) );
		books.add( new Book(2, "Refactoring", "Martin Fowler", 464) );
		books.add( new Book(3, "Head first design patterns", "Eric Freeman, Beth Robson", 694) );
		books.add( new Book(4, "Clean code", "Robert C. Martin", 288) );
	}

	@GET
	public String viewBooks() {

		String html = "<h1>Recommended books</h1>";
		html += "<ul>";
		for (Book book : books) {
			html += "<li><a href='/books/" + book.getId() + "'>" + book.getTitle() + "</a></li>";
		}
		html += "</ul>";

		return html;
	}

	@GET
	@Path("{id}")
	public String viewBook(@PathParam("id") int id) {

		Book book = books.get(id - 1);

		String html = "<h1>" + book.getTitle() + "</h1>";
		html += "<p>Author: " + book.getAuthor() + "</p>";
		html += "<p>Pages: " + book.getNumPages() + "</p>";

		return html;
	}
}
