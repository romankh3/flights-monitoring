package com.github.romankh3.flightsmonitoring.service.impl;


import static com.github.romankh3.flightsmonitoring.service.impl.UniRestServiceImpl.CURRENCIES_KEY;
import static com.github.romankh3.flightsmonitoring.service.impl.UniRestServiceImpl.PLACES_KEY;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.romankh3.flightsmonitoring.FlightClientException;
import com.github.romankh3.flightsmonitoring.dto.CarrierDto;
import com.github.romankh3.flightsmonitoring.dto.CurrencyDto;
import com.github.romankh3.flightsmonitoring.dto.FlightPricesResponse;
import com.github.romankh3.flightsmonitoring.dto.PlaceDto;
import com.github.romankh3.flightsmonitoring.dto.QuoteDto;
import com.github.romankh3.flightsmonitoring.dto.ValidationErrorDto;
import com.github.romankh3.flightsmonitoring.service.FlightPricesClient;
import com.github.romankh3.flightsmonitoring.service.UniRestService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Service
public class FlightPricesClientImpl implements FlightPricesClient {

    public static final String BROWSE_QUOTES_FORMAT = "/apiservices/browsequotes/v1.0/%s/%s/%s/%s/%s/%s";
    public static final String OPTIONAL_BROWSE_QUOTES_FORMAT = BROWSE_QUOTES_FORMAT + "?inboundpartialdate=%s";

    public static final String QUOTES_KEY = "Quotes";
    public static final String ROUTES_KEY = "Routes";
    public static final String DATES_KEY = "Dates";
    public static final String CARRIERS_KEY = "Carriers";
    public static final String VALIDATIONS_KEY = "ValidationErrors";

    @Autowired
    private UniRestService uniRestService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public FlightPricesResponse browseQuotes(String country, String currency, String locale, String originPlace,
            String destinationPlace, String outboundPartialDate) {

        HttpResponse<JsonNode> response = uniRestService.get(String
                .format(BROWSE_QUOTES_FORMAT, country, currency, locale, originPlace, destinationPlace,
                        outboundPartialDate));
        return mapToObject(response);
    }

    public FlightPricesResponse browseQuotes(String country, String currency, String locale, String originPlace,
            String destinationPlace, String outboundPartialDate, String inboundPartialDate) {
        HttpResponse<JsonNode> response = uniRestService.get(String
                .format(OPTIONAL_BROWSE_QUOTES_FORMAT, country, currency, locale, originPlace, destinationPlace,
                        outboundPartialDate, inboundPartialDate));
        return mapToObject(response);
    }

    private FlightPricesResponse mapToObject(HttpResponse<JsonNode> response) {
        if (response.getStatus() == 400) {
            return FlightPricesResponse.builder()
                    .validationErrors(readValue(response.getBody().getObject().get(VALIDATIONS_KEY).toString(),
                                    new TypeReference<List<ValidationErrorDto>>() {}))
                    .build();
        }
        return FlightPricesResponse.builder()
                .quotas(readValue(response.getBody().getObject().get(QUOTES_KEY).toString(),
                        new TypeReference<List<QuoteDto>>() {
                        }))
                .carriers(readValue(response.getBody().getObject().get(CARRIERS_KEY).toString(),
                        new TypeReference<List<CarrierDto>>() {
                        }))
                .places(readValue(response.getBody().getObject().get(PLACES_KEY).toString(),
                        new TypeReference<List<PlaceDto>>() {
                        }))
                .currencies(readValue(response.getBody().getObject().get(CURRENCIES_KEY).toString(),
                        new TypeReference<List<CurrencyDto>>() {
                        }))
                .build();
    }

    private <T> List<T> readValue(String resultAsString, TypeReference<List<T>> valueTypeRef) {
        List<T> list;
        try {
            list = objectMapper.readValue(resultAsString, valueTypeRef);
        } catch (IOException e) {
            throw new FlightClientException("Object Mapping failure.", e);
        }
        return list;
    }
}
