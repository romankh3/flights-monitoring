package com.github.romankh3.flightsmonitoring.service;

import com.github.romankh3.flightsmonitoring.client.dto.FlightPricesResponse;
import com.github.romankh3.flightsmonitoring.repository.entity.Subscription;

/**
 * Service, for getting details based on {@link Subscription} object.
 */
public interface FlightPriceService {

    /**
     * Finds minPrice based on {@link Subscription}.
     *
     * @param flightPricesResponse provided {@link FlightPricesResponse} object.
     * @return
     */
    Integer findMinPrice(FlightPricesResponse flightPricesResponse);

    /**
     * Finds all the flight data related to {@link Subscription} object.
     *
     * @param subscription provided {@link Subscription} object
     * @return {@link FlightPricesResponse} with all the data related to flight specific in {@link Subscription}.
     */
    FlightPricesResponse findFlightPrice(Subscription subscription);
}
