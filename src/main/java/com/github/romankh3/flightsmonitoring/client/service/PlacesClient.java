package com.github.romankh3.flightsmonitoring.client.service;

import com.github.romankh3.flightsmonitoring.client.dto.PlaceDto;
import com.github.romankh3.flightsmonitoring.client.dto.PlacesDto;
import java.util.List;

/**
 * SkyScanner client.
 */
public interface PlacesClient {

    /**
     * Get a list of places that match a query string based on arguments.
     *
     * @param query the code of the city.
     * @param country the code of the country.
     * @param currency the code of the currency.
     * @param locale the code of the locale.
     * @return the collection of the {@link PlaceDto} objects.
     */
    List<PlacesDto> retrieveListPlaces(String query, String country, String currency, String locale);
}
