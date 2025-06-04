package com.example.chatclient.application.port.inbound;

import com.example.chatclient.domain.model.ChatMessage;

public interface ChatRestApi {
    String sendMessage(ChatMessage message);
}
