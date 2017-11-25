package com.github.sigrist.spotify;

import com.github.sigrist.spotify.impl.SpotifyImpl;

public class SpotifyTest {

	public static void main(String[] args) {
		Spotify spotify = new SpotifyImpl(
				"BQB9t_mDs8GodCuABPEd6mAmkUH5rZDXV4CpOAUoEPjiM8jOCBbqdCZC8HOxPIftRq9SMT9OWcwXjOq1vuqjDBGzwOv2QO-s7fxmdli2zVLWLk1zICxYyc0jwgf9-OOUfVUbM0nGzalSSGUh-_RKhK0mw9fxXf_CIUDy5eY9N3iBdH-FfQBCGwrRbiyKX6DjQnyIifBVtdZ7jWI1cjDw8AH2lQ-wng7t0FHxGrmOO8A3bBCBuimpIb3n6AHSLRq2FG5c1zX_I8yYqlA");

		CurrentUser me = spotify.users().me();

		System.out.println(me.displayName());
		System.out.println(me.uri());
		System.out.println(me.id());

		// TODO Aqui eh mais interessante ter tudo ou o usuario eh obrigado a saber que
		// eh paginado!?
		Artists artists = me.artists();
		FollowedArtists followedArtists = artists.following();

		followedArtists.forEach(artist -> {
			System.out.println(artist.id() + ": " + artist.name() + " -> " + artist.popularity());
		});

		User diego = spotify.users().get("diego.salvaia");
		System.out.println(diego.displayName());
		System.out.println(diego.uri());
		System.out.println(diego.id());

		followedArtists = artists.following();
		followedArtists.forEach(artist -> {
			System.out.println(artist.id() + ": " + artist.name() + " -> " + artist.popularity());
		});

	}
}
