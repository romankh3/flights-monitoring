package com.github.romankh3.flightsmonitoring.service;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

/**
 * Service, which is manipulating with Rest calls.
 */
public interface UniRestService {
    HttpResponse<JsonNode> get(String path);
}
