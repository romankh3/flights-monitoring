package com.github.romankh3.flightsmonitoring.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.romankh3.flightsmonitoring.dto.CountryDto;
import com.github.romankh3.flightsmonitoring.dto.CurrencyDto;
import com.github.romankh3.flightsmonitoring.dto.PlaceDto;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.util.List;
import lombok.Data;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Data
@Service
public class ScyScannerClientImpl implements ScyScannerClient {

    @Autowired
    private UniRestService uniRestService;

    private ObjectMapper objectMapper = new ObjectMapper();

    public static final String PLACES_FORMAT = "/apiservices/autosuggest/v1.0/%s/%s/%s/?query=%s";
    public static final String CURRENCIES_FORMAT = "/apiservices/reference/v1.0/currencies";
    public static final String COUNTRIES_FORMAT = "/apiservices/reference/v1.0/countries/%s";

    public static final String PLACES_KEY = "Places";
    public static final String CURRENCIES_KEY = "Currencies";
    public static final String COUNTRIES_KEY = "Countries";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CurrencyDto> retrieveCurrencies() throws IOException, UnirestException {

        HttpResponse<JsonNode> response = uniRestService.get(CURRENCIES_FORMAT);
        if(response.getStatus() != HttpStatus.SC_OK) {
            return null;
        }

        String jsonList = response.getBody().getObject().get(CURRENCIES_KEY).toString();

        return objectMapper.readValue(jsonList, new TypeReference<List<CurrencyDto>>(){});
    }

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

        return objectMapper.readValue(jsonList, new TypeReference<List<PlaceDto>>() {});
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CountryDto> retrieveCountries(String locale) throws IOException, UnirestException {
        HttpResponse<JsonNode> response = uniRestService.get(String.format(COUNTRIES_FORMAT, locale));

        if(response.getStatus() != HttpStatus.SC_OK) {
            return null;
        }

        String jsonList = response.getBody().getObject().get(COUNTRIES_KEY).toString();

        return objectMapper.readValue(jsonList, new TypeReference<List<CountryDto>>(){});
    }
}
