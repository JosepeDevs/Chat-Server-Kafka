package com.example.chatclient.domain.service; // Restored package

import com.example.chatclient.domain.model.ChatMessage;
// ChatService interface is in the same package
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

    @Override
    public void processReceivedMessage(ChatMessage message) {
        // For now, just log the received message
        // In a real application, this could involve more complex business logic
        log.info("Processing received message in domain service: From: {}, Message: {}", message.getFrom(), message.getMessage());
        // Example:
        // if (message.getMessage().contains("urgent")) {
        //     // do something special
        // }
    }
}
