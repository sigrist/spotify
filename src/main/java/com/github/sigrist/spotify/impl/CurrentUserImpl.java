package com.github.sigrist.spotify.impl;

import com.github.sigrist.spotify.Artists;
import com.github.sigrist.spotify.CurrentUser;
import com.github.sigrist.spotify.Spotify;

public class CurrentUserImpl extends UserImpl implements CurrentUser {

	public CurrentUserImpl(Spotify spotify) {
		super(spotify);
	}

	@Override
	public Artists artists() {
		return new ArtistsImpl(spotify());
	}


}
