package com.github.romankh3.flightsmonitoring.service;

import com.github.romankh3.flightsmonitoring.dto.Locale;
import com.github.romankh3.flightsmonitoring.dto.PlaceDto;
import com.mashape.unirest.http.exceptions.UnirestException;
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
public class PlacesClientIT {

    @Autowired
    private PlacesClient placesClient;

    @Test
    public void testPlaces() throws IOException, UnirestException {
        List<PlaceDto> places = placesClient.retrieveListPlaces("Kyiv", "UK", "GBP", Locale.EN_GB);
        Assert.assertFalse(places.isEmpty());
        Assert.assertEquals(10, places.size());
    }
}
