package com.codethen.dropwizard.sample.database;

import java.util.List;

/**
 * Manages an entity in the database
 */
public interface GenericDao<T, ID> {

	/** Returns an object by id, or null if doesn't exist */
	T getById(ID id);

	List<T> getAll();

	T create(T object);

	T update(T object);

	void delete(ID id);
}
