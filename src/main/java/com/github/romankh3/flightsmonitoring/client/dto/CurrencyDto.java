package com.github.romankh3.flightsmonitoring.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Data transfer object for Currency.
 */
@Data
public class CurrencyDto {

    @JsonProperty("Code")
    private String code;

    @JsonProperty("Symbol")
    private String symbol;

    @JsonProperty("ThousandsSeparator")
    private String thousandsSeparator;

    @JsonProperty("DecimalSeparator")
    private String decimalSeparator;

    @JsonProperty("SymbolOnLeft")
    private boolean symbolOnLeft;

    @JsonProperty("SpaceBetweenAmountAndSymbol")
    private boolean spaceBetweenAmountAndSymbol;

    @JsonProperty("RoundingCoefficient")
    private int roundingCoefficient;

    @JsonProperty("DecimalDigits")
    private int decimalDigits;
}
