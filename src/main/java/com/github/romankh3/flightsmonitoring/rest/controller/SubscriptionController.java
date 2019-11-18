package com.github.romankh3.flightsmonitoring.rest.controller;

import com.github.romankh3.flightsmonitoring.rest.dto.SubscriptionDto;
import com.github.romankh3.flightsmonitoring.service.SubscriptionService;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public @ResponseBody SubscriptionDto subscribe(@RequestBody @Valid SubscriptionDto dto) {
        return subscriptionService.subscribe(dto);
    }

    @GetMapping("/{username}")
    public @ResponseBody List<SubscriptionDto> getAllSubscription(@PathVariable final String username) {
        return subscriptionService.findSubscribeByUsername(username);
    }
}
