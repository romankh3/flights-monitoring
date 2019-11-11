package com.github.romankh3.flightsmonitoring.service;

import com.github.romankh3.flightsmonitoring.dto.Currency;
import com.github.romankh3.flightsmonitoring.dto.Place;
import java.io.IOException;
import java.util.List;

/**
 * todo add javadoc
 */
public interface FlightsCalls {

    List<Currency> retrieveCurrencies() throws IOException;

    List<Place> retrieveListPlaces(String query, String country, String currency, String locale) throws IOException;
}
