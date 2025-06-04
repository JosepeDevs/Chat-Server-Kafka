package com.example.chatclient.application.port.outbound;

import com.example.chatclient.domain.model.ChatMessage;

public interface ChatMessageProducer {
    void sendMessage(ChatMessage message);
}
