package com.github.romankh3.flightsmonitoring.service;

import com.github.romankh3.flightsmonitoring.rest.dto.SubscriptionDto;
import java.util.List;

/**
 * Manipulates with  subscriptions.
 */
public interface SubscriptionService {

    /**
     * Add new subscription.
     * @param dto the dto of the subscription.
     */
    SubscriptionDto subscribe(SubscriptionDto dto);

    /**
     * Get all subscription based on username.
     *
     * @param username provided username;
     * @return the collection of the {@link SubscriptionDto} objects.
     */
    List<SubscriptionDto> findSubscribeByUsername(String username);
}
