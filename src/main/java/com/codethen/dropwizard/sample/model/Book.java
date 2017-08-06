package com.codethen.dropwizard.sample.model;

public class Book {

	private int id;
	private String title;
	private String author;
	private int numPages;


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
}
