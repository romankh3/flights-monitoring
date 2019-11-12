package com.github.romankh3.flightsmonitoring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data transfer object for Carrier.
 */
public class CarrierDto {

    @JsonProperty("CarrierId")
    private Integer carrierId;

    @JsonProperty("Name")
    private String name;
}
