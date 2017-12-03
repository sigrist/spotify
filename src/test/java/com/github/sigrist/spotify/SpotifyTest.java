package com.github.sigrist.spotify;

import com.github.sigrist.spotify.impl.SpotifyImpl;

public class SpotifyTest {

	public static void main(String[] args) {
		Spotify spotify = new SpotifyImpl(
				"BQBqsGZYDPfhLApxUdyLx4T2J4T4yZ2no_LnnxckbgQpe7Ff7850XjAsm_BW376soATffWYs4l3kYRyC_u_Gkbymhdym83xlUMhw52VjWBKqkIPYXT40oB2opFW_beJiVe5_l7XuB0hsVaq76wbETTA6vFkWug4S5sSwjQBlOGzVnxi2RhJ_gb3RH1yXICQYuWZulpd8oII5h84ULrYe47B_y4KHPZrFl9wBOWmI3SZAw5lkBAQgRnUFVvDGSRi6BO-E2ulWZzIM1MvIcg");

		
		Profile profile = spotify.profile();

//		CurrentUser me = spotify.users().me();
		
		CurrentUser me = profile.me();
		

		System.out.println(me.displayName());
		System.out.println(me.uri());
		System.out.println(me.id());

		System.out.println("Artistas");
		FollowedArtists followedArtists = profile.followedArtists();

		followedArtists.forEach(artist -> {
			System.out.println(artist.id() + ": " + artist.name() + " -> " + artist.popularity());
		});

		
		System.out.println("Playlists");
		FollowedPlaylists followedPlaylists = profile.followedPlaylists();

		followedPlaylists.forEach(playlist -> {
			System.out.println(playlist.id()+": "+playlist.name() + "("+playlist.publicPlaylist()+")");
		});

		//
//		User diego = spotify.users().get("diego.salvaia");
//		System.out.println(diego.displayName());
//		System.out.println(diego.uri());
//		System.out.println(diego.id());
//
//		Artist angra = me.artists().artist("7IAXZaLTb6nkJr8RmVPn5y");
//		
//		System.out.println(angra.id());
//		System.out.println(angra.name());
//		System.out.println(angra.popularity());
//		
//		Albums albuns = angra.albuns();

	}
}
