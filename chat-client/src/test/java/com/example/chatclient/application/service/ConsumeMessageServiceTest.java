package com.example.chatclient.application.service;

import com.example.chatclient.domain.model.ChatMessage;
import com.example.chatclient.domain.service.ChatService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ConsumeMessageServiceTest {

    @Mock
    private ChatService chatService; // Domain service

    @InjectMocks
    private ConsumeMessageService consumeMessageService;

    @Test
    void consumeMessage_shouldCallChatServiceProcessReceivedMessage() {
        // Given
        ChatMessage message = new ChatMessage("testUser", "Hello from Kafka!");

        // When
        consumeMessageService.consumeMessage(message);

        // Then
        verify(chatService, times(1)).processReceivedMessage(message);
    }

    @Test
    void consumeMessage_withDifferentMessage_shouldAlsoCallChatService() {
        // Given
        ChatMessage specificMessage = new ChatMessage("anotherKafkaUser", "Another specific content.");

        // When
        consumeMessageService.consumeMessage(specificMessage);

        // Then
        verify(chatService, times(1)).processReceivedMessage(specificMessage);
    }
}
