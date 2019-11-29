package com.github.romankh3.flightsmonitoring.client.exception;

import com.github.romankh3.flightsmonitoring.client.dto.ValidationErrorDto;
import java.util.List;
import lombok.ToString;

/**
 * A {@link RuntimeException} that is thrown in case of an flight monitoring failures.
 */
@ToString
public final class FlightSearchClientException extends RuntimeException {

    public FlightSearchClientException(String message) {
        super(message);
    }

    public FlightSearchClientException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public FlightSearchClientException(String message, List<ValidationErrorDto> errors) {
        super(message);
        this.validationErrorDtos = errors;
    }

    private List<ValidationErrorDto> validationErrorDtos;
}
