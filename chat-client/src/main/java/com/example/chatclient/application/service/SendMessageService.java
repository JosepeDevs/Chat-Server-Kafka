package com.example.chatclient.application.service;

import com.example.chatclient.domain.port.in.SendMessageUseCase; // Updated import
import com.example.chatclient.domain.port.out.ChatMessageProducer; // Updated import
import com.example.chatclient.domain.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendMessageService implements SendMessageUseCase {

    private final ChatMessageProducer chatMessageProducer;

    @Override
    public String sendMessage(ChatMessage message) {
        log.info("SendMessageService: Sending message: {}", message);
        // Optional: any application-specific logic before sending
        // For example, transforming the message or adding metadata
        chatMessageProducer.sendMessage(message);
        return "Message sent successfully via SendMessageService";
    }
}
