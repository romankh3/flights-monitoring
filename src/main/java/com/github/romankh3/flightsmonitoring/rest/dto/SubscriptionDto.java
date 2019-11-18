package com.github.romankh3.flightsmonitoring.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Data transfer object to see all the data related to subscription.
 */
@Data
@EqualsAndHashCode(exclude = "minPrice")
public class SubscriptionDto {

    private Long id;

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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate outboundPartialDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate inboundPartialDate;

    private Integer minPrice;
}
