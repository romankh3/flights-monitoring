package com.github.romankh3.flightsmonitoring.service.impl;

import static com.github.romankh3.flightsmonitoring.service.impl.UniRestServiceImpl.PLACES_FORMAT;
import static com.github.romankh3.flightsmonitoring.service.impl.UniRestServiceImpl.PLACES_KEY;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.romankh3.flightsmonitoring.dto.PlaceDto;
import com.github.romankh3.flightsmonitoring.service.PlacesClient;
import com.github.romankh3.flightsmonitoring.service.UniRestService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.util.List;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Service
public class PlacesClientImpl implements PlacesClient {

    @Autowired
    private UniRestService uniRestService;

    @Autowired
    private ObjectMapper objectMapper;


    /**
     * {@inheritDoc}
     */
    @Override
    public List<PlaceDto> retrieveListPlaces(String query, String country, String currency, String locale)
            throws IOException, UnirestException {
        HttpResponse<JsonNode> response = uniRestService
                .get(String.format(PLACES_FORMAT, country, currency, locale, query));

        if (response.getStatus() != HttpStatus.SC_OK) {
            return null;
        }

        String jsonList = response.getBody().getObject().get(PLACES_KEY).toString();

        return objectMapper.readValue(jsonList, new TypeReference<List<PlaceDto>>() {
        });
    }
}
