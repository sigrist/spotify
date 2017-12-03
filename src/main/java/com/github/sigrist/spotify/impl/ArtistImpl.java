package com.github.sigrist.spotify.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.sigrist.spotify.Albums;
import com.github.sigrist.spotify.Artist;
import com.github.sigrist.spotify.Spotify;
import com.github.sigrist.spotify.impl.feign.ArtistFeign;
import com.github.sigrist.spotify.impl.feign.FeignBuilder;

public class ArtistImpl implements Artist {
	private final transient Spotify spotify;
	private final transient ArtistFeign artistFeign;

	private String spotifyId;
	private boolean called;
	private JsonNode jsonNode;
	
	public ArtistImpl(Spotify spotify, JsonNode jsonNode) {
		this(spotify, jsonNode, "", true);
	}
	
	public ArtistImpl(Spotify spotify, String spotifyId) {
		this(spotify, null, spotifyId, false);
	}

	private ArtistImpl(Spotify spotify, JsonNode jsonNode, String spotifyId, Boolean called) {
		this.spotify = spotify;
		this.jsonNode = jsonNode;
		this.spotifyId = spotifyId;
		this.called = called;
		
		
		this.artistFeign = new FeignBuilder<>(ArtistFeign.class, spotify.token()).build();
	}
	
	@Override
	public Spotify spotify() {
		return this.spotify;
	}

	@Override
	public String id() {
		return call().get("id").asText();
	}

	@Override
	public String name() {
		return call().get("name").asText();
	}

	@Override
	public Integer popularity() {
		return call().get("popularity").asInt();
	}

	@Override
	public Albums albuns() {
		return new AlbumsImpl(spotify(), id());
	}
	
	private JsonNode call() {
		// How to cache this call?
		if (!called && !this.spotifyId.isEmpty()) {

			this.jsonNode = this.artistFeign.artist(spotifyId);
			called = true;
		}
		return jsonNode;
	}

}
