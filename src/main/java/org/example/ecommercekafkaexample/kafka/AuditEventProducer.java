package org.example.ecommercekafkaexample.kafka;

import org.example.ecommercekafkaexample.domain.OrderEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AuditEventProducer {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public AuditEventProducer(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(OrderEvent event) {
        kafkaTemplate.send("audit-events", event.getShipmentNumber(), event)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        System.err.println("❌ Failed to send event: " + event.getShipmentNumber());
                    } else {
                        System.out.println("✅ Event sent to Kafka: " + event.getShipmentNumber());
                    }
                });
    }
}
