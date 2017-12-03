package com.github.sigrist.spotify.impl;

import com.github.sigrist.spotify.Profile;
import com.github.sigrist.spotify.Spotify;
import com.github.sigrist.spotify.Users;

public class SpotifyImpl implements Spotify {

	private String token;

	public SpotifyImpl(String token) {
		this.token = token;
	}
	
	@Override
	public String token() {
		return this.token;
	}

	@Override
	public Users users() {
		return new UsersImpl(this);
	}
	
	@Override
	public Profile profile() {
		return new ProfileImpl(this);
	}

}
