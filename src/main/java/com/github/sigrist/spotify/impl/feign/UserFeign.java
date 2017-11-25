package com.github.sigrist.spotify.impl.feign;

import com.fasterxml.jackson.databind.JsonNode;

import feign.Param;
import feign.RequestLine;

public interface UserFeign {
	@RequestLine("GET /v1/me")
	JsonNode me();

	@RequestLine("GET /v1/users/{login}")
	JsonNode user(@Param("login") String login);

}
