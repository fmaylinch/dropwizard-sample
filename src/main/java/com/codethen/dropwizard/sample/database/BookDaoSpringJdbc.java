package com.codethen.dropwizard.sample.database;

import com.codethen.dropwizard.sample.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookDaoSpringJdbc implements BookDao {

	private JdbcTemplate jdbcTemplate;

	private RowMapper<Book> mapper;

	public BookDaoSpringJdbc(DataSource dataSource) {

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.mapper = new RowMapper<Book>() {
			@Nullable
			@Override
			public Book mapRow(ResultSet rs, int rowNum) throws SQLException {

				// Extract a book from current row in ResultSet

				int id = rs.getInt("id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				int numPages = rs.getInt("num_pages");
				Date releaseDate = rs.getDate("release_date");

				return new Book(id, title, author, numPages, releaseDate);
			}
		};
	}

	@Override
	public Book getById(int id) {

		Object[] args = { id };
		return jdbcTemplate.queryForObject("select * from books where id = ?", args, mapper);
	}

	@Override
	public List<Book> getAll() {

		return jdbcTemplate.query("select * from books", mapper);
	}

	@Override
	public List<Book> findByTitle(String titlePart) {

		Object[] args = { "%" + titlePart + "%" };
		return jdbcTemplate.query("select * from books where title like ?", args, mapper);
	}

	@Override
	public Book create(Book book) {

		Object[] args = { book.getTitle(), book.getAuthor(), book.getNumPages(), book.getReleaseDate() };
		jdbcTemplate.update("insert into books (title, author, num_pages, release_date) values (?, ?, ?, ?)", args);

		// TODO: get ID of created book
		// book.setId(id);

		return book;
	}

	@Override
	public Book update(Book book) {

		Object[] args = { book.getTitle(), book.getAuthor(), book.getNumPages(), book.getReleaseDate(), book.getId() };
		jdbcTemplate.update("update books set title = ?, author = ?, num_pages = ?, release_date = ? where id = ?", args);

		return book;
	}

	@Override
	public void delete(int id) {

		Object[] args = { id };
		jdbcTemplate.update("delete from books where id = ?", args);
	}
}
