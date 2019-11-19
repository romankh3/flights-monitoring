package com.github.romankh3.flightsmonitoring.service;

import com.github.romankh3.flightsmonitoring.repository.entity.Subscription;

/**
 * Service, for getting details based on {@link Subscription} object.
 */
public interface FlightPriceService {

    /**
     * Finds minPrice based on {@link Subscription}.
     *
     * @param subscription provided {@link Subscription} object.
     * @return
     */
    Integer findMinPrice(Subscription subscription);

}
