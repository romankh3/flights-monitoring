package com.github.romankh3.flightsmonitoring.client.service.impl;

import static com.github.romankh3.flightsmonitoring.client.service.impl.UniRestServiceImpl.COUNTRIES_FORMAT;
import static com.github.romankh3.flightsmonitoring.client.service.impl.UniRestServiceImpl.COUNTRIES_KEY;
import static com.github.romankh3.flightsmonitoring.client.service.impl.UniRestServiceImpl.CURRENCIES_FORMAT;
import static com.github.romankh3.flightsmonitoring.client.service.impl.UniRestServiceImpl.CURRENCIES_KEY;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.romankh3.flightsmonitoring.client.dto.CountryDto;
import com.github.romankh3.flightsmonitoring.client.dto.CurrencyDto;
import com.github.romankh3.flightsmonitoring.client.exception.FlightSearchClientException;
import com.github.romankh3.flightsmonitoring.client.service.LocalisationClient;
import com.github.romankh3.flightsmonitoring.client.service.UniRestService;
import java.io.IOException;
import java.util.List;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * {@inheritDoc}
 */
@Slf4j
@Component
public class LocalisationClientImpl implements LocalisationClient {

    @Autowired
    private UniRestService uniRestService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CountryDto> retrieveCountries(String locale) {
        HttpResponse<JsonNode> response = uniRestService.get(String.format(COUNTRIES_FORMAT, locale));

        if (response.getStatus() != HttpStatus.SC_OK) {
            return null;
        }

        String jsonList = response.getBody().getObject().get(COUNTRIES_KEY).toString();

        try {
            return objectMapper.readValue(jsonList, new TypeReference<List<CountryDto>>() {
            });
        } catch (IOException e) {
            log.error("Error with reading value from jsonList", e);
            throw new FlightSearchClientException("Error with reading value from jsonList", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CurrencyDto> retrieveCurrencies() {

        HttpResponse<JsonNode> response = uniRestService.get(CURRENCIES_FORMAT);
        if (response.getStatus() != HttpStatus.SC_OK) {
            return null;
        }

        String jsonList = response.getBody().getObject().get(CURRENCIES_KEY).toString();

        try {
            return objectMapper.readValue(jsonList, new TypeReference<List<CurrencyDto>>() {
            });
        } catch (IOException e) {
            log.error("Error with reading value from jsonList", e);
            throw new FlightSearchClientException("Error with reading value from jsonList", e);
        }
    }
}
