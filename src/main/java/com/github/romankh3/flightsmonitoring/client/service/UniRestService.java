package com.github.romankh3.flightsmonitoring.client.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

/**
 * Service, which is manipulating with Rest calls.
 */
public interface UniRestService {

    HttpResponse<JsonNode> get(String path);

}
