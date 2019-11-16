package com.github.romankh3.flightsmonitoring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data transfer object Quota.
 */
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
