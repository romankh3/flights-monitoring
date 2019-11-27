package com.github.romankh3.flightsmonitoring.rest.controller;

import static com.github.romankh3.flightsmonitoring.rest.controller.SubscriptionController.SUBSCRIPTION_CONTROLLER_EP;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.romankh3.flightsmonitoring.client.dto.Locale;
import com.github.romankh3.flightsmonitoring.exception.FlightClientException;
import com.github.romankh3.flightsmonitoring.rest.dto.SubscriptionCreateDto;
import com.github.romankh3.flightsmonitoring.rest.dto.SubscriptionDto;
import com.github.romankh3.flightsmonitoring.rest.dto.SubscriptionUpdateDto;
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
    public void shouldCRUDSubscription() throws Exception {
        SubscriptionCreateDto createDto = new SubscriptionCreateDto();
        createDto.setEmail("kremenec.andru@gmail.com");
        createDto.setCountry("UA");
        createDto.setCurrency("UAH");
        createDto.setLocale(Locale.RU_RU);
        createDto.setOriginPlace("HRK-sky");
        createDto.setDestinationPlace("KBP-sky");
        createDto.setOutboundPartialDate(LocalDate.now().plusMonths(1));

        MockHttpServletRequestBuilder createRequest = MockMvcRequestBuilders
                .post(SUBSCRIPTION_CONTROLLER_EP)
                .content(objectMapper.writeValueAsString(createDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE);

        //Create subscription
        SubscriptionDto savedDto = objectMapper
                .readValue(performAndConvertToString(createRequest), SubscriptionDto.class);

        Assert.assertNotNull(savedDto);
        Assert.assertEquals(createDto.getEmail(), savedDto.getEmail());
        Assert.assertEquals(createDto.getCountry(), savedDto.getCountry());
        Assert.assertEquals(createDto.getCurrency(), savedDto.getCurrency());
        Assert.assertEquals(createDto.getLocale(), savedDto.getLocale());
        Assert.assertEquals(createDto.getDestinationPlace(), savedDto.getDestinationPlace());
        Assert.assertEquals(createDto.getInboundPartialDate(), savedDto.getInboundPartialDate());
        Assert.assertEquals(createDto.getOutboundPartialDate(), savedDto.getOutboundPartialDate());
        Assert.assertEquals(createDto.getOriginPlace(), savedDto.getOriginPlace());

       //Create the same subscription
        mockMvc.perform(createRequest);

        MockHttpServletRequestBuilder getAllByEmailRequest = MockMvcRequestBuilders
                .get(SUBSCRIPTION_CONTROLLER_EP + "/" + createDto.getEmail())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE);

        //Get all subscriptions based on email
        List<SubscriptionDto> subscriptions = readValue(performAndConvertToString(getAllByEmailRequest),
                new TypeReference<List<SubscriptionDto>>() {
                });

        Assert.assertEquals(1, subscriptions.size());
        Assert.assertEquals(createDto.getEmail(), subscriptions.get(0).getEmail());
        Assert.assertEquals(createDto.getCountry(), subscriptions.get(0).getCountry());
        Assert.assertEquals(createDto.getCurrency(), subscriptions.get(0).getCurrency());
        Assert.assertEquals(createDto.getLocale(), subscriptions.get(0).getLocale());
        Assert.assertEquals(createDto.getDestinationPlace(), subscriptions.get(0).getDestinationPlace());
        Assert.assertEquals(createDto.getInboundPartialDate(), subscriptions.get(0).getInboundPartialDate());
        Assert.assertEquals(createDto.getOutboundPartialDate(), subscriptions.get(0).getOutboundPartialDate());
        Assert.assertEquals(createDto.getOriginPlace(), subscriptions.get(0).getOriginPlace());

        SubscriptionUpdateDto updatedDto = new SubscriptionUpdateDto();
        updatedDto.setEmail("kremenec.andru@gmail.com");
        updatedDto.setCountry("RU");
        updatedDto.setCurrency("RUB");
        updatedDto.setLocale(Locale.RU_RU);
        updatedDto.setOriginPlace("KBP-sky");
        updatedDto.setDestinationPlace("HRK-sky");
        updatedDto.setOutboundPartialDate(LocalDate.now().plusMonths(2));

        MockHttpServletRequestBuilder updateRequest = MockMvcRequestBuilders.put(SUBSCRIPTION_CONTROLLER_EP + "/" + subscriptions.get(0).getId())
                .content(objectMapper.writeValueAsString(updatedDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(updateRequest);

        subscriptions = readValue(performAndConvertToString(getAllByEmailRequest),
                new TypeReference<List<SubscriptionDto>>() {
                });

        Assert.assertEquals(1, subscriptions.size());
        Assert.assertEquals(updatedDto.getEmail(), subscriptions.get(0).getEmail());
        Assert.assertEquals(updatedDto.getCountry(), subscriptions.get(0).getCountry());
        Assert.assertEquals(updatedDto.getCurrency(), subscriptions.get(0).getCurrency());
        Assert.assertEquals(updatedDto.getLocale(), subscriptions.get(0).getLocale());
        Assert.assertEquals(updatedDto.getDestinationPlace(), subscriptions.get(0).getDestinationPlace());
        Assert.assertEquals(updatedDto.getInboundPartialDate(), subscriptions.get(0).getInboundPartialDate());
        Assert.assertEquals(updatedDto.getOutboundPartialDate(), subscriptions.get(0).getOutboundPartialDate());
        Assert.assertEquals(updatedDto.getOriginPlace(), subscriptions.get(0).getOriginPlace());

        MockHttpServletRequestBuilder removeRequest = MockMvcRequestBuilders
                .delete(SUBSCRIPTION_CONTROLLER_EP + "/" + subscriptions.get(0).getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(removeRequest);

        subscriptions = readValue(performAndConvertToString(getAllByEmailRequest),
                new TypeReference<List<SubscriptionDto>>() {
                });

        Assert.assertTrue(subscriptions.isEmpty());
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
