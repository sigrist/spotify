package com.github.sigrist.spotify.impl.feign;

import feign.Feign;
import feign.jackson.JacksonDecoder;

public class FeignBuilder<T> {

	private Class<T> clazz;
	private String token;

	public FeignBuilder(Class<T> clazz, String token) {
		this.clazz = clazz;
		this.token = token;
	}

	public T build() {
		// TODO Cache by clazz/token?
		return Feign.builder().requestInterceptor(new AuthRequestInterceptor(token)).decoder(new JacksonDecoder())
				.target(clazz, "https://api.spotify.com/");
	}
}
