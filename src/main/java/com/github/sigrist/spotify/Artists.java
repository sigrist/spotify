package com.github.sigrist.spotify;

public interface Artists {

	FollowedArtists following();
	
	Artist artist(String spotifyId);
}
