package com.codethen.dropwizard.sample.model;

public class Book {

	private int id;
	private String title;
	private String author;
	private int numPages;

	// Default constructor necessary for DW jersey POSTs
	public Book() {
	}

	public Book(int id, String title, String author, int numPages) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.numPages = numPages;
	}


	public int getId() {
		return id;
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

	@Override
	public String toString() {
		return title + " by " + author + " (" + numPages + " pages)";
	}
}
