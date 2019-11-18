package com.github.romankh3.flightsmonitoring.repository.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "subscription")
public class Subscription implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String country;
    private String currency;
    private String locale;
    private String originPlace;
    private String destinationPlace;
    private String outboundPartialDate;
    private String inboundPartialDate;
}
