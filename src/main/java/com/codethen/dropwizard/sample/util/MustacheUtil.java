package com.codethen.dropwizard.sample.util;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Utility for processing Mustache templates.
 */
public class MustacheUtil {

	/**
	 * Takes template from filename and substitutes the placeholders using the given value.
	 * The value can be a collection, a map or another object (getters will be used).
	 */
	public static String processTemplate(String filename, Object value) {

		MustacheFactory mf = new DefaultMustacheFactory();
		Mustache mustache = mf.compile("templates/mustache/" + filename);
		StringWriter writer = new StringWriter();

		try {
			mustache.execute(writer, value).flush();
			return writer.toString();
		} catch (IOException e) {
			throw new RuntimeException("Error while writing template result", e);
		}
	}
}
