package com.github.romankh3.flightsmonitoring.rest.controller;

import com.github.romankh3.flightsmonitoring.rest.dto.SubscriptionDto;
import com.github.romankh3.flightsmonitoring.service.SubscriptionService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(SubscriptionController.SUBSCRIPTION_CONTROLLER_EP)
@Controller
public class SubscriptionController {

    public static final String SUBSCRIPTION_CONTROLLER_EP = "/subscribe";

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping
    public @ResponseBody
    SubscriptionDto create(@RequestBody @Valid SubscriptionDto dto) {
        return subscriptionService.create(dto);
    }

    @GetMapping("/{email}")
    public @ResponseBody
    List<SubscriptionDto> getAllSubscription(@PathVariable final String email) {
        return subscriptionService.findSubscribeByEmail(email);
    }

    @PutMapping("/{subscriptionId}")
    public SubscriptionDto update(@PathVariable final Long subscriptionId,
            @RequestBody @Valid SubscriptionDto dto) {
        return subscriptionService.update(subscriptionId, dto);
    }

    @DeleteMapping("/{subscriptionId}")
    public void delete(@PathVariable final Long subscriptionId) {
        subscriptionService.delete(subscriptionId);
    }
}
