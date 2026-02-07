package org.example.ecommercekafkaexample.kafka;

import org.example.ecommercekafkaexample.domain.OrderEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class OrderEventProducer {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderEventProducer(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(OrderEvent event) {
        System.out.println("➡️ KAFKA SEND: " + event);
        kafkaTemplate.send(
                KafkaTopics.ORDER_EVENTS,
                event.getShipmentNumber(),
                event
        );
    }
}
