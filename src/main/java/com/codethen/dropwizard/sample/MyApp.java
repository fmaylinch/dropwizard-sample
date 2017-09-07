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

		// Configures Dropwizard to serve /assets folder form url path /
		// We also configure default index file as "books.html", although
		//   this is usually "index.html".
		//
		bootstrap.addBundle(new AssetsBundle("/assets/", "/", "books.html"));
	}

	@Override
	public void run(MyAppConfig config, Environment env) throws Exception {

		BookService bookService = new BookService();

		env.jersey().register(new BookController(bookService));
		env.jersey().register(new BookApi(bookService));
	}
}
