package com.github.sigrist.spotify.impl;

import com.github.sigrist.spotify.CurrentUser;
import com.github.sigrist.spotify.FollowedArtists;
import com.github.sigrist.spotify.FollowedPlaylists;
import com.github.sigrist.spotify.Profile;
import com.github.sigrist.spotify.SavedAlbums;
import com.github.sigrist.spotify.SavedTracks;
import com.github.sigrist.spotify.Spotify;
import com.github.sigrist.spotify.TopArtists;
import com.github.sigrist.spotify.TopTracks;

public class ProfileImpl implements Profile {

	private final transient Spotify spotify;
	
	public ProfileImpl(Spotify spotify) {
		this.spotify = spotify;
	}
	
	
	@Override
	public Spotify spotify() {
		return this.spotify;
	}
	
	@Override
	public CurrentUser me() {
		return new CurrentUserImpl(spotify);
	}

	@Override
	public FollowedArtists followedArtists() {
		return new FollowedArtistsImpl(spotify());
	}

	@Override
	public FollowedPlaylists followedPlaylists() {
		return new FollowedPlaylistsImpl(spotify());
	}

	@Override
	public SavedTracks savedTracks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SavedAlbums savedAlbums() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TopArtists topArtists() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TopTracks topTracks() {
		// TODO Auto-generated method stub
		return null;
	}

}
