package org.example.ecommercekafkaexample.kafka;

import org.example.ecommercekafkaexample.audit.AuditEventEntity;
import org.example.ecommercekafkaexample.audit.AuditEventRepository;
import org.example.ecommercekafkaexample.domain.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AuditEventConsumer {

    private final AuditEventRepository auditRepository;
    private final OrderEventProducer orderEventProducer;

    public AuditEventConsumer(AuditEventRepository auditRepository,
                              OrderEventProducer orderEventProducer) {
        this.auditRepository = auditRepository;
        this.orderEventProducer = orderEventProducer;
    }

    @KafkaListener(
            topics = "audit-events",
            groupId = "audit-worker-group",
            concurrency = "5"
    )
    public void consume(OrderEvent event) {
        System.out.println("⬅️ Kafka audit event received: " + event.getShipmentNumber());

        AuditEventEntity entity = new AuditEventEntity(
                event.getShipmentNumber(),
                event.getRecipientEmail(),
                event.getRecipientCountryCode(),
                event.getSenderCountryCode(),
                event.getStatusCode()
        );
        auditRepository.save(entity);

        orderEventProducer.send(event);
    }
}

