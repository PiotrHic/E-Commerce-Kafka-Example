package org.example.ecommercekafkaexample.api;

import jakarta.validation.Valid;
import org.example.ecommercekafkaexample.audit.AuditEventEntity;
import org.example.ecommercekafkaexample.audit.AuditQueue;
import org.example.ecommercekafkaexample.domain.OrderEvent;
import org.example.ecommercekafkaexample.kafka.OrderEventProducer;
import org.example.ecommercekafkaexample.service.OrderEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderEventController {

    private final OrderEventService orderEventService;

    public OrderEventController(OrderEventService orderEventService) {
        this.orderEventService = orderEventService;
    }

    @PostMapping("/events")
    public ResponseEntity<String> receiveOrderEvent(@RequestBody @Valid OrderEvent event) {
        System.out.println ("➡️ HTTP RECEIVED: " + event);

        boolean saved = orderEventService.offerAudit ( event );
        if (!saved) {
            return ResponseEntity.status (500)
                    .body ( "❌ Event was not saved to DB or queue is full" );
        }

        return ResponseEntity.accepted()
                .body("✅ Event accepted and saved to DB");
    }
}