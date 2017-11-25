package com.github.sigrist.spotify.impl;

import com.github.sigrist.spotify.CurrentUser;
import com.github.sigrist.spotify.Spotify;
import com.github.sigrist.spotify.User;
import com.github.sigrist.spotify.Users;

public class UsersImpl implements Users {

	private Spotify spotify;
	
	public UsersImpl(Spotify spotify) {
		this.spotify = spotify;
	}
	
	@Override
	public CurrentUser me() {
		return new CurrentUserImpl(this.spotify);
	}

	@Override
	public User get(String login) {
		return new UserImpl(this.spotify, login);
	}

}
