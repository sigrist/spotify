package com.github.sigrist.spotify;

public interface Users {

	CurrentUser me();
	
	User get(String login);
}
