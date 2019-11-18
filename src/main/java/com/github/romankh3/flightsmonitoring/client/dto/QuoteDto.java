package com.github.romankh3.flightsmonitoring.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Data transfer object Quota.
 */
@Data
public class QuoteDto {

    @JsonProperty("QuoteId")
    private Integer quoteId;

    @JsonProperty("MinPrice")
    private Integer minPrice;

    @JsonProperty("Direct")
    private Boolean direct;

    @JsonProperty("OutboundLeg")
    private LegDto outboundLeg;

    @JsonProperty("InboundLeg")
    private LegDto inboundLeg;

    @JsonProperty("QuoteDateTime")
    private String quoteDateTime;
}
