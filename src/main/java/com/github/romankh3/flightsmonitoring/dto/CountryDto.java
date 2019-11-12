package com.github.romankh3.flightsmonitoring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Data transfer object for Country.
 */
@Data
public class CountryDto {

    @JsonProperty("Code")
    private String code;

    @JsonProperty("Name")
    private String name;
}
