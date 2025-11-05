package com.example.chatclient.domain.port.in; // Updated package

import com.example.chatclient.domain.model.ChatMessage;

public interface KafkaMessageConsumer {
    void consumeMessage(ChatMessage message);
}
