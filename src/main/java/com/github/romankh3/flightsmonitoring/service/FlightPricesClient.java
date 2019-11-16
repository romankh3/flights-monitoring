package com.github.romankh3.flightsmonitoring.service;

import com.github.romankh3.flightsmonitoring.dto.FlightPricesResponse;

/**
 * Browse flight prices.
 */
public interface FlightPricesClient {

    FlightPricesResponse browseQuotes(String country, String currency, String locale, String originPlace,
            String destinationPlace, String outBoundPartialDate);

    FlightPricesResponse browseQuotes(String country, String currency, String locale, String originPlace,
            String destinationPlace, String outboundPartialDate, String inboundPartialDate);
}
