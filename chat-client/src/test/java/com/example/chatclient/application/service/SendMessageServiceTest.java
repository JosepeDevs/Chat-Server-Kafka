package com.example.chatclient.application.service;

import com.example.chatclient.domain.model.ChatMessage;
import com.example.chatclient.domain.port.out.ChatMessageProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class SendMessageServiceTest {

    @Mock
    private ChatMessageProducer chatMessageProducer;

    @InjectMocks
    private SendMessageService sendMessageService;

    @Test
    void sendMessage_shouldCallMessageProducer() {
        // Given
        ChatMessage message = new ChatMessage("testUser", "Hello World!");

        // When
        String result = sendMessageService.sendMessage(message);

        // Then
        verify(chatMessageProducer, times(1)).sendMessage(message);
        assertEquals("Message sent successfully via SendMessageService", result);
    }

    @Test
    void sendMessage_withDifferentMessage_shouldCallMessageProducerWithCorrectMessage() {
        // Given
        ChatMessage specificMessage = new ChatMessage("anotherUser", "Specific content here.");

        // When
        String result = sendMessageService.sendMessage(specificMessage);

        // Then
        verify(chatMessageProducer, times(1)).sendMessage(specificMessage);
        assertEquals("Message sent successfully via SendMessageService", result);
    }
}
