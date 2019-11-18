package com.github.romankh3.flightsmonitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FlightsMonitoringApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightsMonitoringApplication.class, args);
    }
}
