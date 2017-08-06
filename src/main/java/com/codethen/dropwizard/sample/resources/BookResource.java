package com.codethen.dropwizard.sample.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/books")
@Produces(MediaType.TEXT_HTML)
public class BookResource {

	@GET
	public String viewBooks() {
		return "<h1>Recommended books</h1>";
	}
}
