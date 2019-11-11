package com.github.romankh3.flightsmonitoring.service;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Slf4j
@Service
public class UniRestServiceImpl implements UniRestService {

    public static final String HOST = "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com";

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpResponse<JsonNode> get(String path) {
        HttpResponse<JsonNode> response = Unirest.get(HOST + path)
                .header("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                .header("x-rapidapi-key", "8c78203b8bmshfb1bcfddd95d0f9p1d08dfjsnff95927bab34")
                .asJson();

        log.info("Response from Get request, on path={}, response={}", path, response);
        return response;
    }
}
