package com.github.sigrist.spotify.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.sigrist.spotify.Albums;
import com.github.sigrist.spotify.Spotify;
import com.github.sigrist.spotify.impl.feign.AlbumsFeign;
import com.github.sigrist.spotify.impl.feign.ArtistFeign;
import com.github.sigrist.spotify.impl.feign.FeignBuilder;

public class AlbumsImpl implements Albums {

	private final transient Spotify spotify;
	private final transient AlbumsFeign albumsFeign;
	private String artistId;
	private JsonNode jsonNode;
	private boolean called;

	public AlbumsImpl(Spotify spotify, String artistId) {
		this.spotify = spotify;
		this.artistId = artistId;
		this.called = false;
		
		this.albumsFeign = new FeignBuilder<>(AlbumsFeign.class, spotify.token()).build();
	}
	
	@Override
	public Spotify spotify() {
		return this.spotify;
	}

	private JsonNode call() {
		// How to cache this call?
		if (!called && !this.artistId.isEmpty()) {

			this.jsonNode = this.albumsFeign.artistAlbums(this.artistId);
			called = true;
		}
		return jsonNode;
	}


}
