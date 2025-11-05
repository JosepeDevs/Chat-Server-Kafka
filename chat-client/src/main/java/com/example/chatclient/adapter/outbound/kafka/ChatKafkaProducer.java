package com.example.chatclient.adapter.outbound.kafka;

import com.example.chatclient.domain.port.out.ChatMessageProducer; // Updated import
import com.example.chatclient.domain.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ChatKafkaProducer implements ChatMessageProducer {

    private final KafkaTemplate<String, ChatMessage> kafkaTemplate;

    @Value("${spring.kafka.producer.topic}")
    private String topic;

    @Override
    public void sendMessage(ChatMessage message) {
        log.info("Sending message to Kafka topic {}: {}", topic, message);
        try {
            kafkaTemplate.send(topic, message);
        } catch (Exception e) {
            log.error("Error sending message to Kafka: {}", message, e);
            // Consider re-throwing a custom exception or implementing a retry mechanism
        }
    }
}
