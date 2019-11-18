package com.github.romankh3.flightsmonitoring.service;

import com.github.romankh3.flightsmonitoring.repository.entity.Subscription;

public interface FlightPriceService {

    Integer findMinPrice(Subscription subscription);

}
