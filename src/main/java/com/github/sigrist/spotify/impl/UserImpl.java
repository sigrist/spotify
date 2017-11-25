package com.github.sigrist.spotify.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.sigrist.spotify.Spotify;
import com.github.sigrist.spotify.User;
import com.github.sigrist.spotify.impl.feign.FeignBuilder;
import com.github.sigrist.spotify.impl.feign.UserFeign;

public class UserImpl implements User {

	private final transient Spotify spotify;

	private final transient UserFeign userFeign;
	

	private boolean called = false;
	
	private String login;

	private JsonNode jsonNode;

	public UserImpl(Spotify spotify) {
		this(spotify, "");
	}
	
	public UserImpl(Spotify spotify, String login) {
		this.spotify = spotify;
		this.login = login;
		this.userFeign = new FeignBuilder<UserFeign>(UserFeign.class, spotify.token()).build();
		
	}

	@Override
	public Spotify spotify() {
		return this.spotify;
	}

	@Override
	public String displayName() {
		return call().get("display_name").asText();
	}

	@Override
	public String id() {
		return call().get("id").asText();
	}

	@Override
	public String uri() {
		return call().get("uri").asText();
	}
	
	private JsonNode call() {
		// How to cache this call?
		if (!called) {
			
			if (this.login.isEmpty()) {
				this.jsonNode = this.userFeign.me();
			} else {
				this.jsonNode = this.userFeign.user(login);
			}
			called = true;
		}
		return jsonNode;
	}

}
