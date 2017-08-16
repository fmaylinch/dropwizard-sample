package com.codethen.dropwizard.sample.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.StringWriter;

/**
 * Utility for processing FreeMarker templates.
 */
public class FreeMarkerUtil {

	private static final Configuration cfg = buildConfig();

	/**
	 * Takes template from filename and substitutes the placeholders using the given value.
	 * The value can be a collection, a map or another object (getters will be used).
	 */
	public static String processTemplate(String filename, Object value) {

		try {
			final StringWriter writer = new StringWriter();
			final Template template = cfg.getTemplate(filename + ".ftl");
			template.process(value, writer);
			return writer.toString();
		} catch (Exception e) {
			throw new RuntimeException("Error while writing template result", e);
		}
	}

	/** Based on: http://freemarker.org/docs/pgui_quickstart_createconfiguration.html */
	private static Configuration buildConfig() {

		final Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);

		// Read templates from resources folder -- https://stackoverflow.com/a/31117170/1121497
		cfg.setClassForTemplateLoading(FreeMarkerUtil.class, "/templates/freemarker/");

		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setLogTemplateExceptions(false);

		return cfg;
	}
}
