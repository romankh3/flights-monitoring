package com.github.romankh3.flightsmonitoring.service;

import com.github.romankh3.flightsmonitoring.dto.CountryDto;
import com.github.romankh3.flightsmonitoring.dto.CurrencyDto;
import com.github.romankh3.flightsmonitoring.dto.PlaceDto;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.util.List;

/**
 * SkyScanner client.
 */
public interface ScyScannerClient {

    /**
     * Retrieve the currencies that we ScyScanner flight search API.
     *
     * @return the collection of the {@link CurrencyDto} objects.
     *
     * @throws IOException
     */
    List<CurrencyDto> retrieveCurrencies() throws IOException, UnirestException;

    /**
     * Get a list of places that match a query string based on arguments.
     *
     * @param query the code of the city.
     * @param country the code of the country.
     * @param currency the code of the currency.
     * @param locale the code of the locale.
     *
     * @return the collection of the {@link PlaceDto} objects.
     *
     * @throws IOException
     */
    List<PlaceDto> retrieveListPlaces(String query, String country, String currency, String locale)
            throws IOException, UnirestException;

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
}
