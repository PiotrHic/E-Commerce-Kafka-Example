package org.example.ecommercekafkaexample.mockemail;

import org.example.ecommercekafkaexample.domain.EmailNotification;
import org.springframework.stereotype.Service;

@Service
public class MockEmailService implements EmailService {

    @Override
    public void send(EmailNotification notification) {
        try {
            Thread.sleep(50);

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
