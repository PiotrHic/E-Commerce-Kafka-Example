package org.example.ecommercekafkaexample.kafka;

import org.example.ecommercekafkaexample.domain.EmailNotification;
import org.example.ecommercekafkaexample.domain.OrderEvent;
import org.example.ecommercekafkaexample.mockemail.EmailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventConsumer {

    private final EmailService emailService;

    public OrderEventConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(
            topics = "order-events",
            groupId = "order-event-group",
            concurrency = "3"
    )
    public void consume(OrderEvent event) {

        EmailNotification notification = new EmailNotification(
                event.getShipmentNumber(),
                event.getRecipientEmail(),
                event.getRecipientCountryCode(),
                event.getSenderCountryCode(),
                event.getStatusCode()
        );

        emailService.send(notification);

    }
}
