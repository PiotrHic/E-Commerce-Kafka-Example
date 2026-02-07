# Order Event Processing System

Event-driven e-commerce backend built with Spring Boot, Apache Kafka, and PostgreSQL.

## Overview

This project demonstrates an event-driven architecture where order events are:
- received via HTTP
- published to Kafka
- consumed asynchronously
- persisted in a database
- followed by a notification (email mock)

## Tech Stack

- Java 17
- Spring Boot 3
- Spring Kafka
- Apache Kafka (Docker)
- PostgreSQL
- Docker & Docker Compose

## Architecture

![Architecture Diagram](docs/architecture-diagram.png)

## How It Works

1. Client sends HTTP request with order event
2. API publishes event to Kafka topic
3. Kafka consumer processes the event
4. Data is saved to PostgreSQL
5. Email notification is triggered (mock)

## Running Locally (Docker)

### Requirements
- Docker
- Docker Compose


### Start application

```bashs
docker-compose up --build 
```

## Public Deployment

The application is deployed and publicly accessible at:

**URL:** [https://e-commerce-kafka-example.onrender.com](https://e-commerce-kafka-example.onrender.com)

### Testing the endpoint

You can test the order event endpoint by sending a POST request:

```bash
curl -X POST https://e-commerce-kafka-example.onrender.com/api/orders/events \
  -H "Content-Type: application/json" \
  -d '{
        "shipmentNumber": "12345",
        "recipientEmail": "test@example.com",
        "recipientCountryCode": "US",
        "senderCountryCode": "PL",
        "statusCode": 10
      }'

