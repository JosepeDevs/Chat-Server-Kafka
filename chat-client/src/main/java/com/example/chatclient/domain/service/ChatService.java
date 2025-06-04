package com.example.chatclient.domain.service;

import com.example.chatclient.domain.model.ChatMessage;

public interface ChatService {
    void processReceivedMessage(ChatMessage message);
    // We can add a method for sending messages later if business logic is needed before sending
    // ChatMessage prepareMessageForSending(ChatMessage message);
}
