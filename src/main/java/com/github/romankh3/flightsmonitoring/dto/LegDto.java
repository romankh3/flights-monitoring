package com.github.romankh3.flightsmonitoring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Data transfer object for "Leg".
 */
public class LegDto {

    @JsonProperty("OriginId")
    private Integer originId;

    @JsonProperty("DestinationId")
    private Integer destinationId;

    @JsonProperty("DepartureDate")
    private LocalDateTime departureDate;

    @JsonProperty("CarrierIds")
    private List<Integer> carrierIds;
}
