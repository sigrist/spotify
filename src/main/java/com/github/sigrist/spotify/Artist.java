package com.github.sigrist.spotify;

public interface Artist {

	Spotify spotify();
	String id();
	String name();
	Integer popularity();
	
}
