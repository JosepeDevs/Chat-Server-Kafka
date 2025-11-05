package com.example.chatclient.domain.port.out; // Updated package

import com.example.chatclient.domain.model.ChatMessage;

public interface ChatMessageProducer {
    void sendMessage(ChatMessage message);
}
