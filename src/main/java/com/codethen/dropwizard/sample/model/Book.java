package com.codethen.dropwizard.sample.model;

import java.util.Date;

public class Book {

	private int id;
	private String title;
	private String author;
	private int numPages;
	private Date releaseDate;
	private boolean available;


	// Default constructor necessary for DW jersey POSTs
	// If you add another constructor, leave this too
	public Book() {
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getNumPages() {
		return numPages;
	}

	public void setNumPages(int numPages) {
		this.numPages = numPages;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return title + " by " + author + ", " + numPages + " pages " + (available ? "(√)":"(X)");
	}
}
