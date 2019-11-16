package com.github.romankh3.flightsmonitoring.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Integration-level testing sending email.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SendEmailIT {

    @Autowired
    private JavaMailSender javaMailSender;

    @Test
    public void sendEmail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("kremenec.beskrovnyy@gmail.com");
        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);
    }

}
