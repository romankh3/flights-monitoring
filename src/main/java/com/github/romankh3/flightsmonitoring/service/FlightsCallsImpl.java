package com.github.romankh3.flightsmonitoring.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.romankh3.flightsmonitoring.dto.Currency;
import com.github.romankh3.flightsmonitoring.dto.Place;
import java.io.IOException;
import java.util.List;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import lombok.Data;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class FlightsCallsImpl implements FlightsCalls {

    @Autowired
    private UniRestService uniRestService;

    private ObjectMapper objectMapper = new ObjectMapper();

    public static final String PLACES_FORMAT = "/apiservices/autosuggest/v1.0/%s/%s/%s/?query=%s";
    public static final String CURRENCIES_FORMAT = "/apiservices/reference/v1.0/currencies";

    //todo add cache for it!
    @Override
    public List<Currency> retrieveCurrencies() throws IOException {

        HttpResponse<JsonNode> response = uniRestService.get(CURRENCIES_FORMAT);
        if(response.getStatus() != HttpStatus.SC_OK) {
            return null;
        }

        String jsonList = response.getBody().getObject().get("Currencies").toString();


        System.out.println(response);
        return objectMapper.readValue(jsonList, new TypeReference<List<Currency>>(){});
    }

    @Override
    public List<Place> retrieveListPlaces(String query, String country, String currency, String locale)
            throws IOException {
        HttpResponse<JsonNode> response = uniRestService
                .get(String.format(PLACES_FORMAT, country, currency, locale, query));

        if (response.getStatus() != HttpStatus.SC_OK) {
            return null;
        }

        String jsonList = response.getBody().getObject().get("Places").toString();

        return objectMapper.readValue(jsonList, new TypeReference<List<Place>>() {});
    }


}
