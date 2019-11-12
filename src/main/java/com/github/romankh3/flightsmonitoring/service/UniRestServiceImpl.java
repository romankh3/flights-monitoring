package com.github.romankh3.flightsmonitoring.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
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
    public HttpResponse<JsonNode> get(String path) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(HOST + path)
                .header("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                .header("x-rapidapi-key", "8c78203b8bmshfb1bcfddd95d0f9p1d08dfjsnff95927bab34")
                .asJson();

        log.info("Response from Get request, on path={}, response={}", path, response.getBody().toString());
        return response;
    }
}
