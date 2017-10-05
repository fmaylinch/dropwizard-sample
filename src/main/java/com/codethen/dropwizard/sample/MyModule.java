package com.codethen.dropwizard.sample;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.util.Date;

public class MyModule extends SimpleModule {

	public MyModule() {

		addSerializer(Date.class, new JsonSerializer<Date>() {
			@Override
			public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
				jsonGenerator.writeString(date.toString());
			}
		});
	}
}
