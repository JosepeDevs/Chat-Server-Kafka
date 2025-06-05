package com.example.chatclient.domain.port.in; // Updated package

import com.example.chatclient.domain.model.ChatMessage;
import org.springframework.http.ResponseEntity;

public interface ChatRestApi {
    ResponseEntity<String> sendMessage(ChatMessage message);
}
