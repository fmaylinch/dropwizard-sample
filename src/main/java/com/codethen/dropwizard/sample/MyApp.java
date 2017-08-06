package com.codethen.dropwizard.sample;

import io.dropwizard.Application;
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
		// nothing to do yet
	}

	@Override
	public void run(MyAppConfig config, Environment env) throws Exception {
		// nothing to do yet
	}
}
