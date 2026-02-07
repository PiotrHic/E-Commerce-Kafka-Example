package org.example.ecommercekafkaexample.mockemail;

import org.example.ecommercekafkaexample.config.PerformanceProperties;
import org.example.ecommercekafkaexample.domain.EmailNotification;
import org.springframework.stereotype.Service;

@Service
public class MockEmailService implements EmailService {

    private final PerformanceProperties performanceProperties;

    public MockEmailService (PerformanceProperties performanceProperties) {
        this.performanceProperties = performanceProperties;
    }

    @Override
    public void send(EmailNotification notification) {
        try {
            Thread.sleep(performanceProperties.getEmail().getThrottleMs());

            System.out.printf(
                    "[EMAIL MOCK] shipment=%s, email=%s, status=%d%n",
                    notification.shipmentNumber(),
                    notification.recipientEmail(),
                    notification.statusCode()
            );

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
