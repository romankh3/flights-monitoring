package com.github.romankh3.flightsmonitoring.client.service;

import com.github.romankh3.flightsmonitoring.client.dto.CountryDto;
import com.github.romankh3.flightsmonitoring.client.dto.CurrencyDto;
import java.io.IOException;
import java.util.List;

/**
 * Client for SkyScanner localisation.
 */
public interface LocalisationClient {

    /**
     * Retrieve the market countries that SkyScanner flight search API support. Most suppliers (airlines,
     * travel agents and car hire dealers) set their fares based on the market (or country of purchase).
     * It is therefore necessary to specify the market country in every query.
     *
     * @param locale locale of the response.
     *
     * @return the collection of the {@link CountryDto} objects.
     *
     * @throws IOException
     */
    List<CountryDto> retrieveCountries(String locale);

    /**
     * Retrieve the currencies that we ScyScanner flight search API.
     *
     * @return the collection of the {@link CurrencyDto} objects.
     *
     * @throws IOException
     */
    List<CurrencyDto> retrieveCurrencies();

}
