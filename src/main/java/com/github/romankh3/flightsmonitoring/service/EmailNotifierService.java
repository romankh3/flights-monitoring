package com.github.romankh3.flightsmonitoring.service;

import com.github.romankh3.flightsmonitoring.repository.entity.Subscription;

public interface EmailNotifierService {

    void notifySubscriber(Subscription subscription, Integer oldMinPrice, Integer newMinPrice);

    void notifyAddingSubscription(Subscription subscription);
}
