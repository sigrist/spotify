package com.github.sigrist.spotify.impl.feign;

import com.fasterxml.jackson.databind.JsonNode;

import feign.Param;
import feign.RequestLine;

public interface ArtistFeign {

	@RequestLine("GET /v1/me/following?type=artist&limit={limit}")
	JsonNode followedArtists(@Param("limit") Integer limit);

	@RequestLine("GET /v1/me/following?type=artist&limit={limit}&after={after}")
	JsonNode followedArtists(@Param("limit") Integer limit, @Param("after") String after);

}
