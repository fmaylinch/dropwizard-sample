package com.codethen.dropwizard.sample;

import com.codethen.dropwizard.sample.controller.BookApi;
import com.codethen.dropwizard.sample.controller.BookController;
import com.codethen.dropwizard.sample.database.BookDao;
import com.codethen.dropwizard.sample.database.BookDaoJdbc;
import com.codethen.dropwizard.sample.database.BookDaoSpringJdbc;
import com.codethen.dropwizard.sample.service.BookService;
import com.codethen.dropwizard.sample.util.DbUtil;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.sql.DataSource;
import java.util.EnumSet;

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

		// TODO: We could change default Dropwizard Date serialization
		// ObjectMapper mapper = bootstrap.getObjectMapper();
		// mapper.registerModule(new MyModule());
	}

	@Override
	public void run(MyAppConfig config, Environment env) throws Exception {

		DataSource ds = DbUtil.getDataSource();
		BookDao bookDao = new BookDaoSpringJdbc(ds); // choose implementation
		BookService bookService = new BookService(bookDao);

		env.jersey().register(new BookController(bookService));
		env.jersey().register(new BookApi(bookService));

		setupCors(env);
	}

	/**
	 * Enable access from any origin
	 */
	private void setupCors(Environment env)
	{
		final FilterRegistration.Dynamic cors = env.servlets().addFilter("CORS", CrossOriginFilter.class);
		cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
		cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "*");
		cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
		cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "*");
		cors.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");
	}
}
