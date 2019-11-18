package com.github.romankh3.flightsmonitoring.exception;

import com.github.romankh3.flightsmonitoring.client.dto.ValidationErrorDto;
import java.util.List;

/**
 * A {@link RuntimeException} that is thrown in case of an flight monitoring failures.
 */
public final class FlightClientException extends RuntimeException {

    public FlightClientException(String message) {
        super(message);
    }

    public FlightClientException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public FlightClientException(String message, List<ValidationErrorDto> errors) {
        super(message);
        this.validationErrorDtos = errors;
    }

    private List<ValidationErrorDto> validationErrorDtos;
}
