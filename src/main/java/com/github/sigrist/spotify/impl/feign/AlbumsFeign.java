package com.github.sigrist.spotify.impl.feign;

import com.fasterxml.jackson.databind.JsonNode;

import feign.Param;
import feign.RequestLine;

public interface AlbumsFeign {
	@RequestLine("GET /v1/artists/{id}/albums")
	JsonNode artistAlbums(@Param("id") String artistId);

}
