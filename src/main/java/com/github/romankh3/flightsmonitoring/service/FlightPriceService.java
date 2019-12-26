package com.github.romankh3.flightsmonitoring.service;

import com.github.romankh3.flightsmonitoring.repository.entity.Subscription;
import com.github.romankh3.skyscannerflightapiclient.v1.model.browse.BrowseFlightPricesResponseDto;

/**
 * Service, for getting details based on {@link Subscription} object.
 */
public interface FlightPriceService {

    /**
     * Finds minPrice based on {@link Subscription}.
     *
     * @param flightPricesDto provided {@link BrowseFlightPricesResponseDto} object.
     */
    Integer findMinPrice(BrowseFlightPricesResponseDto flightPricesDto);

    /**
     * Finds all the flight data related to {@link Subscription} object.
     *
     * @param subscription provided {@link Subscription} object
     * @return {@link BrowseFlightPricesResponseDto} with all the data related to flight specific in {@link Subscription}.
     */
    BrowseFlightPricesResponseDto findFlightPrice(Subscription subscription);
}
