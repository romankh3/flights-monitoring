package com.github.romankh3.flightsmonitoring.service;

import com.github.romankh3.flightsmonitoring.dto.CountryDto;
import com.github.romankh3.flightsmonitoring.dto.CurrencyDto;
import com.mashape.unirest.http.exceptions.UnirestException;
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
    List<CountryDto> retrieveCountries(String locale) throws IOException, UnirestException;

    /**
     * Retrieve the currencies that we ScyScanner flight search API.
     *
     * @return the collection of the {@link CurrencyDto} objects.
     *
     * @throws IOException
     */
    List<CurrencyDto> retrieveCurrencies() throws IOException, UnirestException;

}
