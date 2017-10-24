package com.codethen.dropwizard.sample.database;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenericDaoSpringJdbc<T, ID> implements GenericDao<T, ID> {
	private JdbcTemplate jdbcTemplate;

	private RowMapper<T> mapper;

	public GenericDaoSpringJdbc(DataSource dataSource) {

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.mapper = new RowMapper<T>() {
			@Nullable
			@Override
			public T mapRow(ResultSet rs, int rowNum) throws SQLException {

				// Extract an object from current row in ResultSet

				// TODO: get properties using reflection
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				int numPages = rs.getInt("num_pages");
				Date releaseDate = rs.getDate("release_date");
				boolean available = rs.getBoolean("available");

				Class<T> type = null; // TODO: we need the type
				T object = null; // TODO: type.newInstance();

				/* TODO: create instance and fill properties
				book.setId(id);
				book.setTitle(title);
				book.setAuthor(author);
				book.setNumPages(numPages);
				book.setReleaseDate(releaseDate);
				book.setAvailable(available);
				*/

				return object;
			}
		};
	}

	@Override
	public T getById(ID id) {

		Object[] args = { id };
		Class<T> type = null; // TODO: we need the type
		String tableName = getTableName(type);
		return jdbcTemplate.queryForObject("select * from " + tableName + " where id = ?", args, mapper);
	}

	@Override
	public List<T> getAll() {
		return jdbcTemplate.query("select * from books", mapper);
	}

	@Override
	public T create(T object) {

		Class<?> type = object.getClass();
		Method[] methods = type.getMethods();

		// TODO: ignore getId (but not for update)
		// In Java getters start with "get" (e.g. getName) or "is" (for booleans, e.g. isAvailable)
		Stream<Method> getters = Arrays.stream(methods)
			.filter(m -> m.getName().startsWith("get") || m.getName().startsWith("is"));

		Object[] args = getters
			.map(m -> invoke(object, m))
			.collect(Collectors.toList())
			.toArray();

		String tableName = getTableName(type);

		String columns = getters
			.map(g -> camelToSnakeCase(g.getName().substring(3)))
			.collect(Collectors.joining(", "));

		String questionMarks = getters
			.map(g -> "?")
			.collect(Collectors.joining(", "));

		jdbcTemplate.update("insert into " + tableName + " (" + columns + ") values (" + questionMarks + ")", args);

		// If we can get the ID from the insert, we could set it
		// object.setId(id);

		return object;
	}

	/** Converts a camelCase identifier (e.g. numberOfPages) to snake_case (e.g. num_of_pages) */
	private String camelToSnakeCase(String camelCaseId) {
		// https://stackoverflow.com/q/2206378/split-and-keep-delimiters
		final String[] parts = camelCaseId.split("(?=[A-Z])"); // e.g. ["num", "Of", "Pages"]
		return Arrays.stream(parts).collect(Collectors.joining("_")).toLowerCase();
	}

	public String getTableName(Class<?> type) {
		return type.getSimpleName().toLowerCase() + "s";
	}

	private Object invoke(T object, Method m) {
		try {
			return m.invoke(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public T update(T object) {

		/* TODO
		Object[] args = { book.getTitle(), book.getAuthor(), book.getNumPages(), book.getReleaseDate(), book.getId() };
		jdbcTemplate.update("update books set title = ?, author = ?, num_pages = ?, release_date = ? where id = ?", args);
		*/

		return object;
	}

	@Override
	public void delete(ID id) {

		Object[] args = { id };
		Class<T> type = null; // TODO: we need the type
		String tableName = getTableName(type);
		jdbcTemplate.update("delete from " + tableName + " where id = ?", args);
	}
}
