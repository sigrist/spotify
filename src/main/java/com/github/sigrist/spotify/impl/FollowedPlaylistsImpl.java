package com.github.sigrist.spotify.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.sigrist.spotify.FollowedPlaylists;
import com.github.sigrist.spotify.Playlist;
import com.github.sigrist.spotify.Spotify;
import com.github.sigrist.spotify.impl.feign.FeignBuilder;
import com.github.sigrist.spotify.impl.feign.PlaylistsFeign;

public class FollowedPlaylistsImpl implements FollowedPlaylists {

	private final transient Spotify spotify;
	private final transient PlaylistsFeign playlistsFeign;
	private Queue<Playlist> buffer;
	private Boolean more;
	private Integer limit;
	private Integer offset;
	private Integer count;
	
	public FollowedPlaylistsImpl(Spotify spotify) {
		this(spotify, 50);
	}
	
	public FollowedPlaylistsImpl(Spotify spotify, Integer limit) {
		this.spotify = spotify;
		this.playlistsFeign = new FeignBuilder<PlaylistsFeign>(PlaylistsFeign.class, spotify.token()).build();

		this.count = 0;
		this.limit = limit;
		this.offset = 1;
		this.more = true;
		this.buffer = new LinkedList<>();
	}
	
	public Spotify spotify() {
		return spotify;
	}

	private void fetch() {
		JsonNode followedPlaylists;
		
		followedPlaylists = playlistsFeign.currentUserPlaylists(limit, offset);
		
		this.more = false;
		if (followedPlaylists.has("items")) {
			
			JsonNode items = followedPlaylists.get("items");
			
			items.forEach(node -> {
				buffer.add(new PlaylistImpl(spotify(), node));
			});
			
			this.count += buffer.size();
			int total = followedPlaylists.get("total").asInt();
			
			if (this.count < (total - 1)) {
				this.more = true;
				this.offset++;
			}
		} 
	}
	
	@Override
	public Iterator<Playlist> iterator() {
		return new Iterator<Playlist>() {

			@Override
			public boolean hasNext() {
				if (buffer.isEmpty() && more) {
					fetch();
				}
				
				return !buffer.isEmpty();
			}

			@Override
			public Playlist next() {
				return buffer.poll();
			}
		};
	}

}
