package org.example.ecommercekafkaexample.service;

import org.example.ecommercekafkaexample.audit.AuditEventEntity;
import org.example.ecommercekafkaexample.audit.AuditEventRepository;
import org.example.ecommercekafkaexample.audit.AuditQueue;
import org.example.ecommercekafkaexample.domain.OrderEvent;
import org.example.ecommercekafkaexample.kafka.OrderEventProducer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderEventService {

    private final AuditEventRepository auditRepository;
    private final OrderEventProducer producer;
    private final AuditQueue auditQueue;

    public OrderEventService(AuditEventRepository auditRepository,
                             OrderEventProducer producer,
                             AuditQueue auditQueue) {
        this.auditRepository = auditRepository;
        this.producer = producer;
        this.auditQueue = auditQueue;
    }

    @Transactional
    public boolean offerAudit(OrderEvent event) {
        AuditEventEntity audit = new AuditEventEntity(
                event.getShipmentNumber(),
                event.getRecipientEmail(),
                event.getRecipientCountryCode(),
                event.getSenderCountryCode(),
                event.getStatusCode()
        );

        boolean accepted = auditQueue.offer(audit);
        if (!accepted) {
            System.err.println("Audit queue full – dropping event");
            return false;
        }

        try {

            auditRepository.save(audit);
            System.out.println("✅ Saved to DB: " + audit.getShipmentNumber());

            producer.send(event);

            return true;
        } catch (Exception e) {
            System.err.println("❌ Failed to save to DB: " + e.getMessage());
            return false;
        }
    }
}
