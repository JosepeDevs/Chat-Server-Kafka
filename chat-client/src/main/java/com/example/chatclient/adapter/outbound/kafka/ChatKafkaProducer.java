package com.example.chatclient.adapter.outbound.kafka;

import com.example.chatclient.application.port.outbound.ChatMessageProducer;
import com.example.chatclient.domain.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ChatKafkaProducer implements ChatMessageProducer {

    private static final Logger logger = LoggerFactory.getLogger(ChatKafkaProducer.class);

    private final KafkaTemplate<String, ChatMessage> kafkaTemplate;

    @Value("${kafka.producer.topic}")
    private String topic;

    @Autowired
    public ChatKafkaProducer(KafkaTemplate<String, ChatMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(ChatMessage message) {
        logger.info("Sending message to Kafka topic {}: {}", topic, message);
        try {
            kafkaTemplate.send(topic, message);
        } catch (Exception e) {
            logger.error("Error sending message to Kafka: {}", message, e);
            // Consider re-throwing a custom exception or implementing a retry mechanism
        }
    }
}
