package com.github.romankh3.flightsmonitoring.service;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.springframework.stereotype.Service;


@Service
public class SkyScannerFlightsMonitoring implements FlightsMonitoring {


    @Override
    public void countries() {
        HttpResponse<String> response = Unirest.get("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browsequotes/v1.0/US/USD/en-US/SFO-sky/JFK-sky/2019-11-25?inboundpartialdate=2019-12-01")
                .header("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                .header("x-rapidapi-key", "8c78203b8bmshfb1bcfddd95d0f9p1d08dfjsnff95927bab34")
                .asString();

        System.out.println(response);
    }
}
