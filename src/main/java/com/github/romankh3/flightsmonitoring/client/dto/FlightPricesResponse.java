package com.github.romankh3.flightsmonitoring.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 * Response object for the Flight Prices.
 */
@Data
public class FlightPricesResponse {

    @JsonProperty("Quotes")
    private List<QuoteDto> quotas;

    @JsonProperty("Places")
    private List<PlaceDto> places;

    @JsonProperty("Carrier")
    private List<CarrierDto> carriers;

    @JsonProperty("Currencies")
    private List<CurrencyDto> currencies;

    @JsonProperty("ValidationErrors")
    private List<ValidationErrorDto> validationErrors;
}
