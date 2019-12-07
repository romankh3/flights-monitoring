package com.github.romankh3.flightsmonitoring.client.service;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

/**
 * Service, which is manipulating with Rest calls.
 */
public interface UniRestService {

    /**
     * Create GET request based on provided {@param path} with needed headers.
     *
     * @param path provided path with all the needed data
     * @return {@link HttpResponse<JsonNode>} response object.
     */
    HttpResponse<JsonNode> get(String path);

}
