package com.example.chatclient.adapter.inbound.rest;

import com.example.chatclient.domain.port.in.ChatRestApi; // Updated import
import com.example.chatclient.domain.port.in.SendMessageUseCase; // Updated import
import com.example.chatclient.domain.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.ExceptionHandler; // Removed
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseStatus; // Removed
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus; // Import this

import javax.validation.Valid; // Import this
// import java.util.stream.Collectors; // Removed

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
@Slf4j
public class ChatController implements ChatRestApi { // Implement the interface

    private final SendMessageUseCase sendMessageUseCase;

    @Override // Add Override annotation
    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@Valid @RequestBody ChatMessage chatMessage) { // Renamed method
        log.info("ChatController: Received POST request to /api/chat/send with message: {}", chatMessage);
        try {
            // The actual sending logic is delegated to the SendMessageUseCase.
            // This controller method fulfills the ChatRestApi interface and handles HTTP concerns.
            String result = sendMessageUseCase.sendMessage(chatMessage);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("ChatController: Error processing chat message via REST: {}", chatMessage, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending message");
        }
    }

    // Removed local ExceptionHandler for MethodArgumentNotValidException,
    // as it's now handled by GlobalExceptionHandler.
}
