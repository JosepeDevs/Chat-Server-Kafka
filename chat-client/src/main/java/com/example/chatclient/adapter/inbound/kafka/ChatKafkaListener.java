package com.example.chatclient.adapter.inbound.kafka;

import com.example.chatclient.domain.port.in.ConsumeMessageUseCase; // Updated import
import com.example.chatclient.domain.port.in.KafkaMessageConsumer; // Updated import
import com.example.chatclient.domain.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ChatKafkaListener implements KafkaMessageConsumer { // Implement the interface

    private final ConsumeMessageUseCase consumeMessageUseCase;

    @Override // Add Override annotation
    @KafkaListener(topics = "${kafka.consumer.topic}", groupId = "${kafka.consumer.group-id}")
    public void consumeMessage(@Payload ChatMessage chatMessage) { // Renamed method
        log.info("ChatKafkaListener: Received message from Kafka: {}", chatMessage);
        try {
            // The actual message consumption logic is delegated to the ConsumeMessageUseCase.
            // This listener method fulfills the KafkaMessageConsumer interface and handles Kafka concerns.
            consumeMessageUseCase.consumeMessage(chatMessage);
        } catch (Exception e) {
            log.error("ChatKafkaListener: Error processing Kafka message: {}", chatMessage, e);
            // Add additional error handling logic if needed (e.g., sending to a dead-letter topic)
        }
    }
}
