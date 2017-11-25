package com.github.sigrist.spotify.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.sigrist.spotify.Artist;
import com.github.sigrist.spotify.Spotify;

public class ArtistImpl implements Artist {
	private final transient Spotify spotify;

	private JsonNode jsonNode;
	
	public ArtistImpl(Spotify spotify, JsonNode jsonNode) {
		this.spotify = spotify;
		this.jsonNode = jsonNode;
	}
	
	@Override
	public Spotify spotify() {
		return this.spotify;
	}

	@Override
	public String id() {
		return jsonNode.get("id").asText();
	}

	@Override
	public String name() {
		return jsonNode.get("name").asText();
	}

	@Override
	public Integer popularity() {
		return jsonNode.get("popularity").asInt();
	}

}
