package com.github.romankh3.flightsmonitoring.service.impl;

import com.github.romankh3.flightsmonitoring.repository.SubscriptionRepository;
import com.github.romankh3.flightsmonitoring.service.EmailNotifierService;
import com.github.romankh3.flightsmonitoring.service.FlightPriceService;
import com.github.romankh3.flightsmonitoring.service.RecountMinPriceService;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecountMinPriceServiceImpl implements RecountMinPriceService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private FlightPriceService flightPriceService;

    @Autowired
    private EmailNotifierService emailNotifierService;

    //todo add async
    @Override
    public void recount() {
        subscriptionRepository.findAll().stream()
                .filter(it -> it.getOutboundPartialDate().isAfter(LocalDate.now().minusDays(1)))
                .forEach(subscription -> {
            Integer newNumPrice = flightPriceService.findMinPrice(subscription);
            if (subscription.getMinPrice() > newNumPrice) {
                emailNotifierService.notifySubscriber(subscription, subscription.getMinPrice(), newNumPrice);
                subscription.setMinPrice(newNumPrice);
                subscriptionRepository.save(subscription);
            }
        });
    }
}
