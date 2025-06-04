package com.example.chatclient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Chat Service API", version = "1.0", description = "API for sending and receiving chat messages"))
public class ChatClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatClientApplication.class, args);
	}

}
