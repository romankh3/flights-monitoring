package com.github.romankh3.flightsmonitoring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Data transfer Object for Validation Errors.
 */
@Data
public class ValidationErrorDto {

    @JsonProperty("ParameterName")
    private String parameterName;

    @JsonProperty("ParameterValue")
    private String parameterValue;

    @JsonProperty("Message")
    private String message;
}
