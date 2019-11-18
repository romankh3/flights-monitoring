package com.github.romankh3.flightsmonitoring.rest.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * Data transfer object to see all the data related to subscription.
 */
@Data
public class SubscribeDto {

    @NotNull
    private String username;

    @NotNull
    private String country;

    @NotNull
    private String currency;

    @NotNull
    private String locale;

    @NotNull
    private String originPlace;

    @NotNull
    private String destinationPlace;

    @NotNull
    @DateTimeFormat(iso = ISO.DATE)
    private String outboundPartialDate;

    @DateTimeFormat(iso = ISO.DATE)
    private String inboundPartialDate;
}
