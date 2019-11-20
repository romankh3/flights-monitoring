package com.github.romankh3.flightsmonitoring.service.impl;

import com.github.romankh3.flightsmonitoring.repository.SubscriptionRepository;
import com.github.romankh3.flightsmonitoring.service.EmailNotifierService;
import com.github.romankh3.flightsmonitoring.service.FlightPriceService;
import com.github.romankh3.flightsmonitoring.service.RecountMinPriceService;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Service
public class RecountMinPriceServiceImpl implements RecountMinPriceService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private FlightPriceService flightPriceService;

    @Autowired
    private EmailNotifierService emailNotifierService;

    //todo add async
    /**
     * {@inheritDoc}
     */
    @Override
    public void recount() {
        subscriptionRepository.findAll().forEach(subscription -> {
            if(subscription.getOutboundPartialDate().isAfter(LocalDate.now().minusDays(1))) {
                Integer newNumPrice = flightPriceService.findMinPrice(subscription);
                if (subscription.getMinPrice() > newNumPrice) {
                    emailNotifierService.notifySubscriber(subscription, subscription.getMinPrice(), newNumPrice);
                    subscription.setMinPrice(newNumPrice);
                    subscriptionRepository.save(subscription);
                }
            } else {
                subscriptionRepository.delete(subscription);
            }
        });
    }
}
