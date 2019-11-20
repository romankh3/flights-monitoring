package com.github.romankh3.flightsmonitoring.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.romankh3.flightsmonitoring.service.FlightPriceService;
import lombok.Data;

/**
 * Data transfer object using in {@link FlightPriceService}.
 */
@Data
public class PlaceDto {

    @JsonProperty("PlaceId")
    private String placeId;

    @JsonProperty("IataCode")
    private String iataCode;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("SkyscannerCode")
    private String skyscannerCode;

    @JsonProperty("CityName")
    private String cityName;

    @JsonProperty("CityId")
    private String cityId;

    @JsonProperty("CountryName")
    private String countryName;
}
