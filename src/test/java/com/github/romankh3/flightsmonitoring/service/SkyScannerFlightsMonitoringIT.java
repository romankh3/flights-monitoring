package com.github.romankh3.flightsmonitoring.service;

import com.github.romankh3.flightsmonitoring.dto.Currency;
import com.github.romankh3.flightsmonitoring.dto.Place;
import java.io.IOException;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SkyScannerFlightsMonitoringIT {

    @Autowired
    private FlightsCalls flightsCalls;

    @Test
    public void testPlaces() throws IOException {
        List<Place> places = flightsCalls.retrieveListPlaces("Stockholm", "UK", "GBP", "en-GB");
        Assert.assertFalse(places.isEmpty());
        Assert.assertEquals(5, places.size());
    }

    @Test
    public void testCurrencies() throws IOException {
        List<Currency> currencies = flightsCalls.retrieveCurrencies();
        Assert.assertFalse(currencies.isEmpty());
    }
}
