package com.github.romankh3.flightsmonitoring.service.impl;

import com.github.romankh3.flightsmonitoring.client.dto.FlightPricesResponse;
import com.github.romankh3.flightsmonitoring.repository.SubscriptionRepository;
import com.github.romankh3.flightsmonitoring.repository.entity.Subscription;
import com.github.romankh3.flightsmonitoring.rest.dto.SubscriptionCreateDto;
import com.github.romankh3.flightsmonitoring.rest.dto.SubscriptionDto;
import com.github.romankh3.flightsmonitoring.rest.dto.SubscriptionUpdateDto;
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
    public SubscriptionDto create(SubscriptionCreateDto dto) {
        Subscription subscription = toEntity(dto);
        Optional<Subscription> one = subscriptionRepository.findOne(Example.of(subscription));

        if (one.isPresent()) {
            log.info("The same subscription has been found for Subscription={}", subscription);
            Subscription fromDatabase = one.get();
            return toDto(fromDatabase);
        } else {

            FlightPricesResponse flightPrice = flightPriceService.findFlightPrice(subscription);
            subscription.setMinPrice(flightPriceService.findMinPrice(flightPrice));
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
    public List<SubscriptionDto> findByEmail(String email) {
        return subscriptionRepository.findByEmail(email).stream().map(this::toDto).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long subscriptionId) {
        subscriptionRepository.deleteById(subscriptionId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubscriptionDto update(Long subscriptionId, SubscriptionUpdateDto dto) {
        Subscription subscription = subscriptionRepository.getOne(subscriptionId);
        subscription.setDestinationPlace(dto.getDestinationPlace());
        subscription.setOriginPlace(dto.getOriginPlace());
        subscription.setLocale(dto.getLocale());
        subscription.setCurrency(dto.getCurrency());
        subscription.setCountry(dto.getCountry());
        subscription.setEmail(dto.getEmail());
        subscription.setOutboundPartialDate(dto.getOutboundPartialDate());
        subscription.setInboundPartialDate(dto.getInboundPartialDate());
        return toDto(subscriptionRepository.save(subscription));
    }

    private Subscription toEntity(SubscriptionCreateDto dto) {
        Subscription subscription = new Subscription();
        subscription.setCountry(dto.getCountry());
        subscription.setCurrency(dto.getCurrency());
        subscription.setDestinationPlace(dto.getDestinationPlace());
        subscription.setInboundPartialDate(dto.getInboundPartialDate());
        subscription.setOutboundPartialDate(dto.getOutboundPartialDate());
        subscription.setLocale(dto.getLocale());
        subscription.setOriginPlace(dto.getOriginPlace());
        subscription.setEmail(dto.getEmail());

        return subscription;
    }

    private SubscriptionDto toDto(Subscription entity) {
        SubscriptionDto dto = new SubscriptionDto();
        dto.setEmail(entity.getEmail());
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
