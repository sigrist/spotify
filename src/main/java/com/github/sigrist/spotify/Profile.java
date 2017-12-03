package com.github.sigrist.spotify;

public interface Profile {

	Spotify spotify();
	
	CurrentUser me();
	
	FollowedArtists followedArtists();
	
	FollowedPlaylists followedPlaylists();
	
	SavedTracks savedTracks();
	
	SavedAlbums savedAlbums();
	
	TopArtists topArtists();
	
	TopTracks topTracks();
}
