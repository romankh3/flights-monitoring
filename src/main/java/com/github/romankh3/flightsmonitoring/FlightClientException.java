package com.github.romankh3.flightsmonitoring;

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
}
