package com.github.romankh3.flightsmonitoring.rest.controller;

import static com.github.romankh3.flightsmonitoring.rest.controller.SubscriptionController.SUBSCRIPTION_CONTROLLER_EP;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.romankh3.flightsmonitoring.client.dto.Locale;
import com.github.romankh3.flightsmonitoring.exception.FlightClientException;
import com.github.romankh3.flightsmonitoring.rest.dto.SubscriptionDto;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class SubscriptionControllerIT {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldAddSubscription() throws Exception {
        SubscriptionDto dto = new SubscriptionDto();
        dto.setUsername("kremenec.andru@gmail.com");
        dto.setCountry("UA");
        dto.setCurrency("UAH");
        dto.setLocale(Locale.RU_RU);
        dto.setOriginPlace("HRK-sky");
        dto.setDestinationPlace("KBP-sky");
        dto.setOutboundPartialDate(LocalDate.now().plusMonths(1));

        MockHttpServletRequestBuilder requestCreateSubscription = MockMvcRequestBuilders
                .post(SUBSCRIPTION_CONTROLLER_EP)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE);
        SubscriptionDto savedDto = objectMapper
                .readValue(performAndConvertToString(requestCreateSubscription), SubscriptionDto.class);

        Assert.assertNotNull(savedDto);
        Assert.assertEquals(dto, savedDto);

        mockMvc.perform(requestCreateSubscription);

        MockHttpServletRequestBuilder requestGetAllSubscriptions = MockMvcRequestBuilders
                .get(SUBSCRIPTION_CONTROLLER_EP + "/" + dto.getUsername())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE);

        List<SubscriptionDto> subscriptions = readValue(performAndConvertToString(requestGetAllSubscriptions),
                new TypeReference<List<SubscriptionDto>>() {
                });

        Assert.assertEquals(1, subscriptions.size());
        Assert.assertEquals(dto, subscriptions.get(0));
    }

    private <T> List<T> readValue(String resultAsString, TypeReference<List<T>> valueTypeRef) {
        List<T> list;
        try {
            list = objectMapper.readValue(resultAsString, valueTypeRef);
        } catch (IOException e) {
            throw new FlightClientException("Object Mapping failure.", e);
        }
        return list;
    }

    private String performAndConvertToString(MockHttpServletRequestBuilder request) {
        try {
            return mockMvc.perform(request)
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse()
                    .getContentAsString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
