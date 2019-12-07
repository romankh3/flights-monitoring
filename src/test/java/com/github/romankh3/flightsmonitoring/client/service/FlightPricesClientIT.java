package com.github.romankh3.flightsmonitoring.client.service;

import com.github.romankh3.flightsmonitoring.client.dto.FlightPricesDto;
import com.github.romankh3.flightsmonitoring.client.dto.Locale;
import com.github.romankh3.flightsmonitoring.client.exception.FlightSearchClientException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightPricesClientIT {

    @Autowired
    private FlightPricesClient flightPricesClient;

    @Test
    public void testBrowseQuota() {
        FlightPricesDto flightPricesDto = flightPricesClient
                .browseQuotes("UA", "UAH", Locale.RU_RU, "HRK-sky", "KBP-sky",
                        LocalDate.now().plusMonths(1).format(DateTimeFormatter.ISO_DATE));

        Assert.assertNotNull(flightPricesDto);
    }

    @Test(expected = FlightSearchClientException.class)
    public void testBrowseQuotaValidation() {
        String date = LocalDate.now().minusDays(1).format(DateTimeFormatter.ISO_DATE);
        flightPricesClient.browseQuotes("UA", "UAH", Locale.RU_RU, "HRK-sky", "KBP-sky", date);
    }
}
