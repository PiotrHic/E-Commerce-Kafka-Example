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
```bash
docker-compose up --build