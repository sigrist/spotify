package com.github.sigrist.spotify.impl;

import com.github.sigrist.spotify.Artist;
import com.github.sigrist.spotify.Artists;
import com.github.sigrist.spotify.Spotify;

public class ArtistsImpl implements Artists {

	private final transient Spotify spotify;

	public ArtistsImpl(Spotify spotify) {
		this.spotify = spotify;

	}

	public Spotify spotify() {
		return spotify;
	}

	@Override
	public FollowedArtistsImpl following() {
		return new FollowedArtistsImpl(spotify());
	}

	@Override
	public Artist artist(String spotifyId) {
		return new ArtistImpl(spotify(), spotifyId);
	}
}
