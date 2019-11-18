package com.github.romankh3.flightsmonitoring.rest.controller;

import com.github.romankh3.flightsmonitoring.rest.dto.SubscribeDto;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SubscribeController {

    @GetMapping
    public String home() {
        return "home";
    }

    @PostMapping("/subscribe")
    public Object subscribe() {
        return null;
    }

    @PostMapping("/{username}")
    public List<SubscribeDto> getAllSubscribes(@PathVariable final String username) {

        return null;
    }
}
