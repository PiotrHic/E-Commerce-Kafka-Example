package org.example.ecommercekafkaexample.domain;

public record EmailNotification(
        String shipmentNumber,
        String recipientEmail,
        String recipientCountryCode,
        String senderCountryCode,
        int statusCode
) {
}
