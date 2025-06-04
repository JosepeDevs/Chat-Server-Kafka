package com.example.chatclient.adapter.inbound.kafka;

import com.example.chatclient.application.port.inbound.KafkaMessageConsumer;
import com.example.chatclient.domain.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ChatKafkaListener {

    private static final Logger logger = LoggerFactory.getLogger(ChatKafkaListener.class);
    private final KafkaMessageConsumer kafkaMessageConsumer;

    @Autowired
    public ChatKafkaListener(KafkaMessageConsumer kafkaMessageConsumer) {
        this.kafkaMessageConsumer = kafkaMessageConsumer;
    }

    @KafkaListener(topics = "${kafka.consumer.topic}", groupId = "${kafka.consumer.group-id}")
    public void listen(@Payload ChatMessage chatMessage) {
        logger.info("Received message from Kafka: {}", chatMessage);
        try {
            kafkaMessageConsumer.consume(chatMessage);
        } catch (Exception e) {
            logger.error("Error processing Kafka message: {}", chatMessage, e);
            // Add additional error handling logic if needed (e.g., sending to a dead-letter topic)
        }
    }
}
