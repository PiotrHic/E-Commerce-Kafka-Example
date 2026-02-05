package org.example.ecommercekafkaexample.api;

import jakarta.validation.Valid;
import org.example.ecommercekafkaexample.domain.OrderEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderEventController {

    @PostMapping("/events")
    public ResponseEntity<Void> receiveOrderEvent(@RequestBody @Valid OrderEvent event) {
        System.out.println("Received event: " + event.getShipmentNumber());
        return ResponseEntity.accepted().build();
    }
}