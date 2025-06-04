package com.example.chatclient.config;

import com.example.chatclient.domain.service.ChatService;
import com.example.chatclient.domain.service.ChatServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainServiceConfig {

    @Bean
    public ChatService chatService() {
        return new ChatServiceImpl();
    }
}
