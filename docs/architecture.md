## System Architecture

The system follows an event-driven architecture.

1. Client sends HTTP request with order event
2. Spring Boot API publishes event to Kafka
3. Kafka consumers process events asynchronously
4. Processed data is stored in PostgreSQL
5. Notification (email mock) is triggered