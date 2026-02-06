package org.example.ecommercekafkaexample.kafka;

import org.example.ecommercekafkaexample.domain.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventConsumer {

    @KafkaListener(
            topics = KafkaTopics.ORDER_EVENTS,
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(OrderEvent event) {

        System.out.println(
                "[EMAIL MOCK] shipment=" + event.getShipmentNumber()
                        + ", email=" + event.getRecipientEmail()
                        + ", status=" + event.getStatusCode()
        );

        try {
            Thread.sleep(200);
        } catch (InterruptedException ignored) {}
    }
}
