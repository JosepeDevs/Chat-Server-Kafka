package com.example.chatclient.domain.service;

import com.example.chatclient.domain.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChatServiceImpl implements ChatService {

    private static final Logger logger = LoggerFactory.getLogger(ChatServiceImpl.class);

    @Override
    public void processReceivedMessage(ChatMessage message) {
        // For now, just log the received message
        // In a real application, this could involve more complex business logic
        logger.info("Processing received message in domain service: From: {}, Message: {}", message.getFrom(), message.getMessage());
        // Example:
        // if (message.getMessage().contains("urgent")) {
        //     // do something special
        // }
    }
}
