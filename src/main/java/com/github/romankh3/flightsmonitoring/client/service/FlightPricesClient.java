package com.github.romankh3.flightsmonitoring.client.service;

import com.github.romankh3.flightsmonitoring.client.dto.FlightPricesDto;

/**
 * Browse flight prices.
 */
public interface FlightPricesClient {

    FlightPricesDto browseQuotes(String country, String currency, String locale, String originPlace,
            String destinationPlace, String outBoundPartialDate);

    FlightPricesDto browseQuotes(String country, String currency, String locale, String originPlace,
            String destinationPlace, String outboundPartialDate, String inboundPartialDate);
}
