package com.github.romankh3.flightsmonitoring.service;

import com.github.romankh3.flightsmonitoring.dto.CountryDto;
import com.github.romankh3.flightsmonitoring.dto.CurrencyDto;
import com.github.romankh3.flightsmonitoring.dto.Locale;
import com.github.romankh3.flightsmonitoring.dto.PlaceDto;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
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
    private PlacesClient placesClient;

    @Autowired
    private LocalisationClient localisationClient;

    @Test
    public void testPlaces() throws IOException, UnirestException {
        List<PlaceDto> places = placesClient.retrieveListPlaces("Stockholm", "UK", "GBP", Locale.EN_GB);
        Assert.assertFalse(places.isEmpty());
        Assert.assertEquals(5, places.size());
    }

    @Test
    public void testCurrencies() throws IOException, UnirestException {
        List<CurrencyDto> currencies = localisationClient.retrieveCurrencies();
        Assert.assertFalse(currencies.isEmpty());
    }

    @Test
    public void testCountries() throws IOException, UnirestException {
        List<CountryDto> countries = localisationClient.retrieveCountries(Locale.EN_GB);
        Assert.assertFalse(countries.isEmpty());
    }

    @Test
    public void test() throws IOException {
        String string = Jsoup.connect("https://cont.ws/@sahalara").get().toString();

        int articleCount = 122;
        if(!string.contains(articleCount + " запис") &&  string.contains((1+articleCount) + " запис")) {
            articleCount++;
            System.out.println(articleCount);
        } else if(string.contains(articleCount + " запис")) {
            System.out.println(articleCount);
        }
    }
}
