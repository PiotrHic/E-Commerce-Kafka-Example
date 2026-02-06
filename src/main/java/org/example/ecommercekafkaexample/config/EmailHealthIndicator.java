package org.example.ecommercekafkaexample.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class EmailHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        boolean ok = true;
        if(ok){
            return Health.up().withDetail("MockEmailService", "Available").build();
        } else {
            return Health.down().withDetail("MockEmailService", "Unavailable").build();
        }
    }
}
