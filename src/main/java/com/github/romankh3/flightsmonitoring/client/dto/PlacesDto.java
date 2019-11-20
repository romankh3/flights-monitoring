package com.github.romankh3.flightsmonitoring.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.romankh3.flightsmonitoring.client.service.PlacesClient;
import lombok.Data;

/**
 * Using for {@link PlacesClient}.
 */
@Data
public class PlacesDto {

    @JsonProperty("PlaceId")
    private String placeId;

    @JsonProperty("PlaceName")
    private String placeName;

    @JsonProperty("CountryId")
    private String countryId;

    @JsonProperty("RegionId")
    private String regionId;

    @JsonProperty("CityId")
    private String cityId;

    @JsonProperty("CountryName")
    private String countryName;
}
