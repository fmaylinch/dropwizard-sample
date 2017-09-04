package com.codethen.dropwizard.sample;

import com.codethen.dropwizard.sample.controller.BookApi;
import com.codethen.dropwizard.sample.controller.BookController;
import com.codethen.dropwizard.sample.service.BookService;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * To start server, run with args: server
 */
public class MyApp extends Application<MyAppConfig> {

	public static void main(String[] args) throws Exception {
		new MyApp().run(args);
	}

	@Override
	public String getName() {
		return "my-app";
	}

	@Override
	public void initialize(Bootstrap<MyAppConfig> bootstrap) {
		bootstrap.addBundle(new AssetsBundle("/assets/", "/assets/"));
	}

	@Override
	public void run(MyAppConfig config, Environment env) throws Exception {

		BookService bookService = new BookService();

		env.jersey().register(new BookController(bookService));
		env.jersey().register(new BookApi(bookService));
	}
}
