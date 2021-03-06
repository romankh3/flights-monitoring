package com.github.romankh3.flightsmonitoring.service;

import com.github.romankh3.flightsmonitoring.repository.entity.Subscription;
import com.github.romankh3.flightsmonitoring.rest.dto.SubscriptionCreateDto;
import com.github.romankh3.flightsmonitoring.rest.dto.SubscriptionDto;
import com.github.romankh3.flightsmonitoring.rest.dto.SubscriptionUpdateDto;
import java.util.List;

/**
 * Manipulates with  subscriptions.
 */
public interface SubscriptionService {

    /**
     * Add new subscription.
     * @param dto the dto of the subscription.
     */
    SubscriptionDto create(SubscriptionCreateDto dto);

    /**
     * Get all subscription based on email.
     *
     * @param email provided email;
     * @return the collection of the {@link SubscriptionDto} objects.
     */
    List<SubscriptionDto> findByEmail(String email);

    /**
     * Remove subscription based on it ID
     *
     * @param subscriptionId the ID of the {@link Subscription}.
     */
    void delete(Long subscriptionId);

    /**
     * Update subscription based on ID
     *
     *
     * @param subscriptionId the ID of the subscription to be updated.
     * @param dto the data to be updated.
     * @return updated {@link SubscriptionDto}.
     */
    SubscriptionDto update(Long subscriptionId, SubscriptionUpdateDto dto);
}
