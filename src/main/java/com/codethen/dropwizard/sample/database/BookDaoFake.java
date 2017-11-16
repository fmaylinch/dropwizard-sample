package com.codethen.dropwizard.sample.database;

import com.codethen.dropwizard.sample.model.Book;

import java.util.*;

public class BookDaoFake implements BookDao {

	private List<Book> books = createFakeBookList();

	private int lastId = books.size();

	@Override
	public List<Book> findByTitle(String titlePart) {
		return null;
	}

	@Override
	public Book getById(Integer id) {
		int index = findIndexOfId(id);
		return books.get(index);
	}

	@Override
	public List<Book> getAll() {
		return books;
	}

	@Override
	public Book create(Book book) {
		book.setId(++lastId);
		books.add(book);
		return book;
	}

	@Override
	public Book update(Book book) {
		int index = findIndexOfId(book.getId());
		books.set(index, book);
		return book;
	}

	@Override
	public void delete(Integer id) {
		int index = findIndexOfId(id);
		books.remove(index);
	}

	private int findIndexOfId(int id) {

		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getId() == id) {
				return i;
			}
		}

		return -1;
	}

	private List<Book> createFakeBookList() {

		ArrayList<Book> result = new ArrayList<>();
		result.add(createBook("Clean Code", "Robert C. Martin", 434));
		result.add(createBook("Refactoring", "Martin Fowler", 431));
		result.add(createBook("Head First Design Patterns", "Eric Freeman", 638));
		result.add(createBook("Effective Java", "Joshua Bloch", 272));
		result.add(createBook("Algorithm Design Manual", "Steven S. Skiena", 486));
		result.add(createBook("Pragmatic Programmer", "Andy Hunt", 321));
		return result;
	}

	private Book createBook(String title, String author, int pages) {
		Book book = new Book();
		book.setId(++lastId);
		book.setTitle(title);
		book.setAuthor(author);
		book.setNumPages(pages);
		return book;
	}
}
