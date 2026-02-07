package org.example.ecommercekafkaexample.api;

import jakarta.validation.Valid;
import org.example.ecommercekafkaexample.audit.AuditEventEntity;
import org.example.ecommercekafkaexample.audit.AuditQueue;
import org.example.ecommercekafkaexample.domain.OrderEvent;
import org.example.ecommercekafkaexample.kafka.OrderEventProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderEventController {

    private final AuditQueue auditQueue;
    private final OrderEventProducer producer;

    public OrderEventController(AuditQueue auditQueue, OrderEventProducer producer) {
        this.auditQueue = auditQueue;
        this.producer = producer;
    }

    @PostMapping("/events")
    public ResponseEntity<Void> receiveOrderEvent(@RequestBody @Valid OrderEvent event) {
        System.out.println("➡️ HTTP RECEIVED: " + event);
        AuditEventEntity audit = new AuditEventEntity (
                event.getShipmentNumber(),
                event.getRecipientEmail(),
                event.getRecipientCountryCode(),
                event.getSenderCountryCode(),
                event.getStatusCode()
        );

        boolean accepted = auditQueue.offer(audit);

        if (!accepted) {
            System.err.println("Audit queue full – dropping event");
        }

        producer.send(event);

        return ResponseEntity.accepted().build();
    }
}