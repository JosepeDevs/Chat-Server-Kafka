package com.example.chatclient.application.service;

import com.example.chatclient.application.port.inbound.ChatRestApi;
import com.example.chatclient.application.port.inbound.KafkaMessageConsumer;
import com.example.chatclient.application.port.outbound.ChatMessageProducer;
import com.example.chatclient.domain.model.ChatMessage;
import com.example.chatclient.domain.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // Mark this as a Spring service component
public class ApplicationChatService implements ChatRestApi, KafkaMessageConsumer {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationChatService.class);

    private final ChatMessageProducer chatMessageProducer;
    private final ChatService chatService; // Domain service

    @Autowired
    public ApplicationChatService(ChatMessageProducer chatMessageProducer, ChatService chatService) {
        this.chatMessageProducer = chatMessageProducer;
        this.chatService = chatService;
    }

    // Implementation for ChatRestApi (inbound port for REST)
    @Override
    public String sendMessage(ChatMessage message) {
        logger.info("Application Service: Sending message from REST API: {}", message);
        // Optional: any application-specific logic before sending
        // For example, transforming the message or adding metadata
        chatMessageProducer.sendMessage(message);
        return "Message sent successfully via Application Service";
    }

    // Implementation for KafkaMessageConsumer (inbound port for Kafka Listener)
    @Override
    public void consume(ChatMessage message) {
        logger.info("Application Service: Consuming message from Kafka: {}", message);
        // Delegate processing to the domain service
        chatService.processReceivedMessage(message);
        // Optional: further application-specific logic after processing
    }
}
