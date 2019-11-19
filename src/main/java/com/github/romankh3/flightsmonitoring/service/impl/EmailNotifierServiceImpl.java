package com.github.romankh3.flightsmonitoring.service.impl;

import com.github.romankh3.flightsmonitoring.repository.entity.Subscription;
import com.github.romankh3.flightsmonitoring.service.EmailNotifierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Slf4j
@Service
public class EmailNotifierServiceImpl implements EmailNotifierService {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifySubscriber(Subscription subscription, Integer oldMinPrice, Integer newMinPrice) {
        log.debug("method notifySubscriber STARTED");
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(subscription.getEmail());
        msg.setSubject("Flights Monitoring Service");
        msg.setText(String.format("Hello, dear! \n "
                + "the price for your flight has decreased \n"
                + "Old min price = %s,\n new min price = %s,\n Subscription details = %s", oldMinPrice, newMinPrice, subscription.toString()));
        javaMailSender.send(msg);
        log.debug("method notifySubscriber FINISHED");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyAddingSubscription(Subscription subscription) {
        log.debug("method notifyAddingSubscription STARTED");
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(subscription.getEmail());
        msg.setSubject("Flights Monitoring Service");
        msg.setText(String.format("Hello, dear! \n "
                + "Subscription has been successfully added. \n"
                + "Subscription details = %s", subscription.toString()));
        javaMailSender.send(msg);
        log.debug("method notifyAddingSubscription FINISHED");
    }
}
