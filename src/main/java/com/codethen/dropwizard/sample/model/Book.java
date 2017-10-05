package com.codethen.dropwizard.sample.model;

import java.util.Date;

public class Book {

	private int id;
	private String title;
	private String author;
	private int numPages;
	private Date releaseDate;

	// Default constructor necessary for DW jersey POSTs
	public Book() {
	}

	public Book(int id, String title, String author, int numPages, Date releaseDate) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.numPages = numPages;
		this.releaseDate = releaseDate;
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

	public String getAuthor() {
		return author;
	}

	public int getNumPages() {
		return numPages;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	@Override
	public String toString() {
		return title + " by " + author + " (" + numPages + " pages)";
	}
}
