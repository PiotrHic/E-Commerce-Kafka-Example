package org.example.ecommercekafkaexample.audit;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "audit_events")
public class AuditEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shipmentNumber;
    private String recipientEmail;
    private String recipientCountryCode;
    private String senderCountryCode;
    private int statusCode;

    private Instant receivedAt;

    protected AuditEventEntity() {}

    public AuditEventEntity(String shipmentNumber,
                            String recipientEmail,
                            String recipientCountryCode,
                            String senderCountryCode,
                            int statusCode) {
        this.shipmentNumber = shipmentNumber;
        this.recipientEmail = recipientEmail;
        this.recipientCountryCode = recipientCountryCode;
        this.senderCountryCode = senderCountryCode;
        this.statusCode = statusCode;
        this.receivedAt = Instant.now();
    }

}
