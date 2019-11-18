package com.github.romankh3.flightsmonitoring.service.impl;

import com.github.romankh3.flightsmonitoring.repository.SubscriptionRepository;
import com.github.romankh3.flightsmonitoring.repository.entity.Subscription;
import com.github.romankh3.flightsmonitoring.rest.dto.SubscriptionDto;
import com.github.romankh3.flightsmonitoring.service.SubscriptionService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public SubscriptionDto subscribe(SubscriptionDto dto) {
        return toDto(subscriptionRepository.save(toEntity(dto)));
    }

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
        return dto;
    }
}
