package org.example.ecommercekafkaexample.api;

import jakarta.validation.Valid;
import org.example.ecommercekafkaexample.audit.AuditEventEntity;
import org.example.ecommercekafkaexample.audit.AuditQueue;
import org.example.ecommercekafkaexample.domain.OrderEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/orders")
public class OrderEventController {

    private final AuditQueue auditQueue;

    public OrderEventController(AuditQueue auditQueue) {
        this.auditQueue = auditQueue;
    }

    @PostMapping("/events")
    public ResponseEntity<Void> receiveOrderEvent(@RequestBody @Valid OrderEvent event) {

        AuditEventEntity audit = new AuditEventEntity (
                event.getShipmentNumber(),
                event.getRecipientEmail(),
                event.getRecipientCountryCode(),
                event.getSenderCountryCode(),
                event.getStatusCode()
        );

        boolean accepted = auditQueue.offer(audit);

        if (!accepted) {
            // system przeciążony – audit dropped (ale API nadal responsywne)
            System.err.println("Audit queue full – dropping event");
        }

        return ResponseEntity.accepted().build();
    }
}