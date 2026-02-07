package org.example.ecommercekafkaexample.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "order_event_audit")
public class OrderEventEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String shipmentNumber;
    private String recipientEmail;
    private String recipientCountryCode;
    private String senderCountryCode;
    private int statusCode;

    private Instant receivedAt;

}

