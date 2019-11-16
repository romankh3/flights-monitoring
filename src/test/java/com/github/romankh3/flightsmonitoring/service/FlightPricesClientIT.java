package com.github.romankh3.flightsmonitoring.service;

import com.github.romankh3.flightsmonitoring.dto.FlightPricesResponse;
import com.github.romankh3.flightsmonitoring.dto.Locale;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Ignore;
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
        FlightPricesResponse flightPricesResponse = flightPricesClient
                .browseQuotes("UA", "UAH", Locale.RU_RU, "HRK-sky", "KBP-sky",
                        LocalDate.now().plusMonths(1).format(DateTimeFormatter.ISO_DATE));

        Assert.assertNotNull(flightPricesResponse);
    }

    @Test
    public void testBrowseQuotaValidation() {
        FlightPricesResponse flightPricesResponse = flightPricesClient
                .browseQuotes("UA", "UAH", Locale.RU_RU, "HRK-sky", "KBP-sky",
                        LocalDate.now().minusDays(1).format(DateTimeFormatter.ISO_DATE));

        Assert.assertNotNull(flightPricesResponse);
        Assert.assertNotNull(flightPricesResponse.getValidationErrors());
        Assert.assertFalse(flightPricesResponse.getValidationErrors().isEmpty());
        Assert.assertEquals("OutboundDate", flightPricesResponse.getValidationErrors().get(0).getParameterName());
        Assert.assertEquals("2019-11-15", flightPricesResponse.getValidationErrors().get(0).getParameterValue());
        Assert.assertEquals("Date in the past", flightPricesResponse.getValidationErrors().get(0).getMessage());
    }
}
