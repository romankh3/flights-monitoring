package com.github.romankh3.flightsmonitoring.rest.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home {@link Controller} for handling root path "/" to redirect to Swagger-UI.
 */
@RequestMapping("/")
@Controller
public class HomeController {

    @ApiOperation("Creates to redirect to swagger-ui.")
    @GetMapping
    public String home() {
        return "redirect:/swagger-ui.html";
    }
}
