package com.example.chatclient.adapter.inbound.kafka;

import com.example.chatclient.domain.model.ChatMessage;
import com.example.chatclient.domain.port.in.ConsumeMessageUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doThrow;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class ChatKafkaListenerTest {

    @Mock
    private ConsumeMessageUseCase consumeMessageUseCase;

    @InjectMocks
    private ChatKafkaListener chatKafkaListener;

    @Test
    void consumeMessage_shouldCallConsumeMessageUseCase() {
        // Given
        ChatMessage message = new ChatMessage("kafkaUser", "Message from Kafka topic.");

        // When
        chatKafkaListener.consumeMessage(message);

        // Then
        verify(consumeMessageUseCase, times(1)).consumeMessage(message);
    }

    @Test
    void consumeMessage_whenUseCaseThrowsException_shouldHandleAndNotPropagate() {
        // Given
        ChatMessage message = new ChatMessage("errorUser", "This message will cause an error in use case.");
        doThrow(new RuntimeException("Simulated use case error")).when(consumeMessageUseCase).consumeMessage(message);

        // When / Then
        // The listener logs the error but shouldn't let the exception propagate
        // beyond the listener method itself (to avoid Kafka consumer error loops if not handled by Kafka error handlers)
        assertDoesNotThrow(() -> {
            chatKafkaListener.consumeMessage(message);
        });

        // Verify that the use case was still called
        verify(consumeMessageUseCase, times(1)).consumeMessage(message);
    }
}
