package com.github.romankh3.flightsmonitoring.client.service.impl;

import static com.github.romankh3.flightsmonitoring.client.service.impl.UniRestServiceImpl.PLACES_FORMAT;
import static com.github.romankh3.flightsmonitoring.client.service.impl.UniRestServiceImpl.PLACES_KEY;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.romankh3.flightsmonitoring.client.dto.PlacesDto;
import com.github.romankh3.flightsmonitoring.client.exception.FlightSearchClientException;
import com.github.romankh3.flightsmonitoring.client.service.PlacesClient;
import com.github.romankh3.flightsmonitoring.client.service.UniRestService;
import java.io.IOException;
import java.util.List;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Slf4j
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
    public List<PlacesDto> retrieveListPlaces(String query, String country, String currency, String locale) {
        HttpResponse<JsonNode> response = uniRestService
                .get(String.format(PLACES_FORMAT, country, currency, locale, query));

        if (response.getStatus() != HttpStatus.SC_OK) {
            return null;
        }

        String jsonList = response.getBody().getObject().get(PLACES_KEY).toString();

        try {
            return objectMapper.readValue(jsonList, new TypeReference<List<PlacesDto>>() {
            });
        } catch (IOException e) {
            log.error("Error with reading value from jsonList", e);
            throw new FlightSearchClientException("Error with reading value from jsonList", e);
        }
    }
}
