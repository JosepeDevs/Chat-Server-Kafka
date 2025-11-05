package com.example.chatclient.adapter.inbound.rest;

import com.example.chatclient.domain.model.ChatMessage;
import com.example.chatclient.domain.port.in.SendMessageUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(ChatController.class)
class ChatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SendMessageUseCase sendMessageUseCase;

    @Autowired
    private ObjectMapper objectMapper; // For converting ChatMessage to JSON

    @Test
    void sendMessage_whenValidPayload_shouldReturnOkAndCallUseCase() throws Exception {
        // Given
        ChatMessage message = new ChatMessage("controllerUser", "Valid message payload.");
        String expectedResponse = "Message sent successfully via SendMessageService"; // Or whatever the use case returns
        when(sendMessageUseCase.sendMessage(any(ChatMessage.class))).thenReturn(expectedResponse);

        // When & Then
        mockMvc.perform(post("/api/chat/send")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(message)))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));

        verify(sendMessageUseCase).sendMessage(any(ChatMessage.class)); // Could use an ArgumentCaptor for more specific verification
    }

    @Test
    void sendMessage_whenInvalidPayload_blankFrom_shouldReturnBadRequest() throws Exception {
        // Given
        ChatMessage message = new ChatMessage("", "Message with blank sender."); // 'from' is blank

        // When & Then
        // This relies on the GlobalExceptionHandler to produce the "VALIDATION_ERROR" response
        mockMvc.perform(post("/api/chat/send")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(message)))
                .andExpect(status().isBadRequest());
                // We could also assert the content of the error response if GlobalExceptionHandler is part of this test slice
                // or if we mock its behavior. @WebMvcTest might not include @ControllerAdvice by default.
                // If GlobalExceptionHandler is not active, this might just be a generic 400 from Spring.
    }

    @Test
    void sendMessage_whenInvalidPayload_blankMessage_shouldReturnBadRequest() throws Exception {
        // Given
        ChatMessage message = new ChatMessage("sender", ""); // 'message' is blank

        // When & Then
        mockMvc.perform(post("/api/chat/send")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(message)))
                .andExpect(status().isBadRequest());
    }
}
