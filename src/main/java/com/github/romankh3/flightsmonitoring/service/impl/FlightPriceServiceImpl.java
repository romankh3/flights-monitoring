package com.github.romankh3.flightsmonitoring.service.impl;

import com.github.romankh3.flightsmonitoring.client.service.FlightPricesClient;
import com.github.romankh3.flightsmonitoring.repository.entity.Subscription;
import com.github.romankh3.flightsmonitoring.service.FlightPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightPriceServiceImpl implements FlightPriceService {

    @Autowired
    private FlightPricesClient flightPricesClient;

    @Override
    public Integer findMinPrice(Subscription subscription) {
        if (subscription.getInboundPartialDate() == null) {
            return flightPricesClient
                    .browseQuotes(subscription.getCountry(), subscription.getCurrency(), subscription.getLocale(),
                            subscription.getOriginPlace(), subscription.getDestinationPlace(),
                            subscription.getOutboundPartialDate().toString()).getQuotas().get(0).getMinPrice();
        } else {
            return flightPricesClient
                    .browseQuotes(subscription.getCountry(), subscription.getCurrency(), subscription.getLocale(),
                            subscription.getOriginPlace(), subscription.getDestinationPlace(),
                            subscription.getOutboundPartialDate().toString(), subscription.getInboundPartialDate().toString()).getQuotas()
                    .get(0).getMinPrice();
        }
    }
}
