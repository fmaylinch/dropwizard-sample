package com.codethen.dropwizard.sample.database;

import com.codethen.dropwizard.sample.model.Book;

import javax.sql.DataSource;

public class BookSimpleDao extends GenericDaoSpringJdbc<Book, Integer> {

	public BookSimpleDao(DataSource dataSource) {
		super(dataSource);
	}
}
