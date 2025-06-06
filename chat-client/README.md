# Chat Server PoC

Sample chat client using SPRING BOOT, KAFKA and hexagonal architecture.

## Features

- Hexagonal Architecture (Ports and Adapters)
- Send and consume chat messages using Kafka
- REST endpoint exposed to send messages
- Serialization and deserialization to JSON
- Unit tests and component tests with JUnit 5 and Mockito

## Requirements

- Java 11+
- Maven 3.6+

## Local run

1. Set the Kafka Broker and the topic to which you want to connect in application.yml file

2. Run the Spring Boot application:

```bash
  mvn spring-boot:run
```

## Usage

### Send schat message

Post to `/api/chat/send`:

```bash
  curl -X POST http://localhost:8081/api/chat/send \
    -H "Content-Type: application/json" \
    -d "{\"from\":\"user1\",\"message\":\"Hello, world!\"}"
```

## Message Consumption
Messages sent are published to the Kafka topic and automatically consumed by the internal listener.

### Important Considerations:

If multiple consumers are present and you want all of them to receive the messages sent on the topic, you MUST use a different group-id for each connected client.

## Tests

Run tests with:

```bash
  mnv test
```