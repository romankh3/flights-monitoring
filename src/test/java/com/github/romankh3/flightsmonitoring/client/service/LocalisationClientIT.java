package com.github.romankh3.flightsmonitoring.client.service;

import com.github.romankh3.flightsmonitoring.client.dto.CountryDto;
import com.github.romankh3.flightsmonitoring.client.dto.CurrencyDto;
import com.github.romankh3.flightsmonitoring.client.dto.Locale;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Integration-level testing for {@link LocalisationClient}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LocalisationClientIT {

    @Autowired
    private LocalisationClient localisationClient;

    @Test
    public void testCurrencies() {
        List<CurrencyDto> currencies = localisationClient.retrieveCurrencies();
        Assert.assertFalse(currencies.isEmpty());
    }

    @Test
    public void testCountries() {
        List<CountryDto> countries = localisationClient.retrieveCountries(Locale.EN_GB);
        Assert.assertFalse(countries.isEmpty());
    }
}
