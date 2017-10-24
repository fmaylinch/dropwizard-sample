package com.codethen.dropwizard.sample;

import com.codethen.dropwizard.sample.model.Book;

import java.lang.reflect.Method;
import java.util.Date;

public class ReflectionExample {

	public static void main(String[] args) throws Exception {

		Book book = new Book();
		book.setId(1);
		book.setTitle("something");
		book.setAuthor("someone");
		book.setNumPages(200);
		book.setReleaseDate(new Date());
		book.setAvailable(true);

		Object obj = book; // Treat book as an object

		Class<?> type = obj.getClass();

		Method[] methods = type.getMethods();

		for (Method method : methods) {
			System.out.println(method.getName());
		}

		Method getTitleMethod = type.getMethod("getTitle");
		Object result = getTitleMethod.invoke(obj);
		System.out.println(result);
	}
}
