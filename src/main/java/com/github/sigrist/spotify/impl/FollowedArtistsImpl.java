package com.github.sigrist.spotify.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.sigrist.spotify.Artist;
import com.github.sigrist.spotify.FollowedArtists;
import com.github.sigrist.spotify.Spotify;
import com.github.sigrist.spotify.impl.feign.ArtistFeign;
import com.github.sigrist.spotify.impl.feign.FeignBuilder;

public class FollowedArtistsImpl implements FollowedArtists {

	private final transient Spotify spotify;
	private final transient ArtistFeign artistFeign;

	private Queue<Artist> buffer;
	private String after;
	private Integer limit;
	private Boolean more;

	public FollowedArtistsImpl(Spotify spotify) {
		this(spotify, "", 50);
	}

	public FollowedArtistsImpl(Spotify spotify, String after, Integer limit) {
		this.spotify = spotify;
		this.artistFeign = new FeignBuilder<ArtistFeign>(ArtistFeign.class, spotify.token()).build();

		this.more = true;
		this.buffer = new LinkedList<>();
		this.after = after;
		this.limit = limit;

	}

	public Spotify spotify() {
		return spotify;
	}

	private void fetch() {
		JsonNode followedArtists;
		
		if (this.after.isEmpty()) {
			followedArtists = artistFeign.followedArtists(limit);
		} else {
			followedArtists = artistFeign.followedArtists(limit, after);
		}

		if (followedArtists.has("artists") && followedArtists.get("artists").has("items")) {

			if (!followedArtists.get("artists").get("cursors").isNull()) {
				this.after = followedArtists.get("artists").get("cursors").get("after").asText();
				this.more = true;
			} else {
				this.more = false;
			}

			JsonNode items = followedArtists.get("artists").get("items");
			items.forEach(node -> {
				buffer.add(new ArtistImpl(spotify(), node));
			});
		}
	}

	@Override
	public Iterator<Artist> iterator() {
		return new Iterator<Artist>() {

			@Override
			public boolean hasNext() {
				if (buffer.isEmpty() && more) {
					fetch();
				}
				
				return !buffer.isEmpty();
			}

			@Override
			public Artist next() {
				return buffer.poll();
			}
		};
	}

}
