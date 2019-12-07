package com.github.romankh3.flightsmonitoring.service.impl;

import com.github.romankh3.flightsmonitoring.repository.entity.Subscription;
import com.github.romankh3.flightsmonitoring.service.FlightPriceService;
import com.github.romankh3.skyscannerflightapiclient.v1.BrowseFlightPricesClient;
import com.github.romankh3.skyscannerflightapiclient.v1.BrowseFlightPricesClientImpl;
import com.github.romankh3.skyscannerflightapiclient.v1.model.browse.BrowseFlightPricesResponseDto;
import com.github.romankh3.skyscannerflightapiclient.v1.model.browse.BrowseSearchDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Service
public class FlightPriceServiceImpl implements FlightPriceService {

    private BrowseFlightPricesClient client = new BrowseFlightPricesClientImpl();

    @Value("${x.rapid.api.key}")
    private String xRapidApiKey;

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer findMinPrice(BrowseFlightPricesResponseDto flightPricesDto) {
        return flightPricesDto.getQuotas().get(0).getMinPrice();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BrowseFlightPricesResponseDto findFlightPrice(Subscription subscription) {
        return client.browseQuotes(xRapidApiKey, toBrowseSearchDto(subscription));
    }

    private BrowseSearchDto toBrowseSearchDto(Subscription subscription) {
        return BrowseSearchDto.hiddenBuilder()
                .country(subscription.getCountry())
                .currency(subscription.getCurrency())
                .destinationPlace(subscription.getDestinationPlace())
                .originPlace(subscription.getOriginPlace())
                .outboundPartialDate(subscription.getOutboundPartialDate())
                .inboundPartialDate(subscription.getInboundPartialDate())
                .locale(subscription.getLocale())
                .build();
    }
}
