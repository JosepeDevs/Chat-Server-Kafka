package com.example.chatclient.application.service;

import com.example.chatclient.domain.port.in.ConsumeMessageUseCase; // Updated import
import com.example.chatclient.domain.model.ChatMessage;
import com.example.chatclient.domain.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumeMessageService implements ConsumeMessageUseCase {

    private final ChatService chatService; // Domain service

    @Override
    public void consumeMessage(ChatMessage message) {
        log.info("ConsumeMessageService: Consuming message: {}", message);
        // Delegate processing to the domain service
        chatService.processReceivedMessage(message);
        // Optional: further application-specific logic after processing
    }
}
