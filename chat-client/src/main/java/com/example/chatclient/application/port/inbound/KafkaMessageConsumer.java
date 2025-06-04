package com.example.chatclient.application.port.inbound;

import com.example.chatclient.domain.model.ChatMessage;

public interface KafkaMessageConsumer {
    void consume(ChatMessage message);
}
