package com.github.sigrist.spotify.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.sigrist.spotify.Playlist;
import com.github.sigrist.spotify.Spotify;

public class PlaylistImpl implements Playlist {

	private final transient Spotify spotify;
	private JsonNode jsonNode;

	public PlaylistImpl(Spotify spotify, JsonNode node) {
		this.spotify = spotify;
		this.jsonNode = node;
	}

	@Override
	public String name() {
		return call().get("name").asText();
	}
	
	@Override
	public String id() {
		return call().get("id").asText();
	}

	@Override
	public Boolean publicPlaylist() {
		return call().get("public").asBoolean();
	}


	
	private JsonNode call() {
		// How to cache this call?
//		if (!called && !this.spotifyId.isEmpty()) {
//
//			this.jsonNode = this.artistFeign.artist(spotifyId);
//			called = true;
//		}
		return jsonNode;
	}

}
