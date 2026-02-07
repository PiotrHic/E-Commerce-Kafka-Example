package org.example.ecommercekafkaexample.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "performance")
public class PerformanceProperties {

    private Email email = new Email();

    public Email getEmail() { return email; }
    public void setEmail(Email email) { this.email = email; }

    public static class Email {
        private int throttleMs;
        private int maxThreads;

        public int getThrottleMs() { return throttleMs; }
        public void setThrottleMs(int throttleMs) { this.throttleMs = throttleMs; }

        public int getMaxThreads() { return maxThreads; }
        public void setMaxThreads(int maxThreads) { this.maxThreads = maxThreads; }
    }
}
