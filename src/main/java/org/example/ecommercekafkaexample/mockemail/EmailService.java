package org.example.ecommercekafkaexample.mockemail;

import org.example.ecommercekafkaexample.domain.EmailNotification;

public interface EmailService {
    void send (EmailNotification notification);
}
