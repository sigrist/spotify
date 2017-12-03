package com.github.sigrist.spotify.impl.feign;

import com.fasterxml.jackson.databind.JsonNode;

import feign.Param;
import feign.RequestLine;

public interface PlaylistsFeign {
	@RequestLine("GET /v1/me/playlists?limit={limit}&offset={offset}")
	JsonNode currentUserPlaylists(@Param("limit") Integer limit, @Param("offset") Integer offset);

}
