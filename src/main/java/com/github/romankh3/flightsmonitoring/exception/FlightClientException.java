package com.github.romankh3.flightsmonitoring.exception;

import com.github.romankh3.skyscannerflightapiclient.v1.model.validation.ValidationErrorsDto;
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

    private List<ValidationErrorsDto> validationErrorDtos;

    public FlightClientException(String message, List<ValidationErrorsDto> errors) {
        super(message);
        this.validationErrorDtos = errors;
    }
}
