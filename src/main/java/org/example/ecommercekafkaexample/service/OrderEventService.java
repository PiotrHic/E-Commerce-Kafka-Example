package org.example.ecommercekafkaexample.service;

import org.example.ecommercekafkaexample.domain.OrderEvent;
import org.example.ecommercekafkaexample.kafka.AuditEventProducer;
import org.springframework.stereotype.Service;

@Service
public class OrderEventService {

    private final AuditEventProducer auditProducer;

    public OrderEventService(AuditEventProducer auditProducer) {
        this.auditProducer = auditProducer;
    }

    public void sendToAuditKafka(OrderEvent event) {
        auditProducer.send(event);
    }
}
