package com.github.romankh3.flightsmonitoring.service.impl;

import com.github.romankh3.flightsmonitoring.repository.SubscriptionRepository;
import com.github.romankh3.flightsmonitoring.repository.entity.Subscription;
import com.github.romankh3.flightsmonitoring.rest.dto.SubscriptionDto;
import com.github.romankh3.flightsmonitoring.service.EmailNotifierService;
import com.github.romankh3.flightsmonitoring.service.FlightPriceService;
import com.github.romankh3.flightsmonitoring.service.SubscriptionService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Slf4j
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private FlightPriceService flightPriceService;

    @Autowired
    private EmailNotifierService emailNotifierService;

    /**
     * {@inheritDoc}
     */
    @Override
    public SubscriptionDto subscribe(SubscriptionDto dto) {
        Subscription subscription = toEntity(dto);
        Optional<Subscription> one = subscriptionRepository.findOne(Example.of(subscription));

        if (one.isPresent()) {
            log.info("The same subscription has been found for Subscription={}", subscription);
            Subscription fromDatabase = one.get();
            return toDto(fromDatabase);
        } else {

            Integer minPrice = flightPriceService.findMinPrice(subscription);
            subscription.setMinPrice(minPrice);
            Subscription saved = subscriptionRepository.save(subscription);
            log.info("Added new subscription={}", saved);
            emailNotifierService.notifyAddingSubscription(subscription);
            return toDto(saved);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SubscriptionDto> findSubscribeByUsername(String username) {
        return subscriptionRepository.findByUsername(username).stream().map(this::toDto).collect(Collectors.toList());
    }

    private Subscription toEntity(SubscriptionDto dto) {
        Subscription subscription = new Subscription();
        subscription.setCountry(dto.getCountry());
        subscription.setCurrency(dto.getCurrency());
        subscription.setDestinationPlace(dto.getDestinationPlace());
        subscription.setInboundPartialDate(dto.getInboundPartialDate());
        subscription.setOutboundPartialDate(dto.getOutboundPartialDate());
        subscription.setLocale(dto.getLocale());
        subscription.setOriginPlace(dto.getOriginPlace());
        subscription.setUsername(dto.getUsername());

        return subscription;
    }

    private SubscriptionDto toDto(Subscription entity) {
        SubscriptionDto dto = new SubscriptionDto();
        dto.setUsername(entity.getUsername());
        dto.setCountry(entity.getCountry());
        dto.setCurrency(entity.getCurrency());
        dto.setLocale(entity.getLocale());
        dto.setOriginPlace(entity.getOriginPlace());
        dto.setDestinationPlace(entity.getDestinationPlace());
        dto.setOutboundPartialDate(entity.getOutboundPartialDate());
        dto.setInboundPartialDate(entity.getInboundPartialDate());
        dto.setMinPrice(entity.getMinPrice());
        dto.setId(entity.getId());
        return dto;
    }
}
