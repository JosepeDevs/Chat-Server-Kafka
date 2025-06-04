package com.example.chatclient.adapter.inbound.rest;

import com.example.chatclient.application.port.inbound.ChatRestApi;
import com.example.chatclient.domain.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus; // Import this

import javax.validation.Valid; // Import this
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
    private final ChatRestApi chatRestApi;

    @Autowired
    public ChatController(ChatRestApi chatRestApi) {
        this.chatRestApi = chatRestApi;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendChatMessage(@Valid @RequestBody ChatMessage chatMessage) {
        logger.info("Received POST request to /api/chat/send with message: {}", chatMessage);
        try {
            String result = chatRestApi.sendMessage(chatMessage);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Error processing chat message via REST: {}", chatMessage, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending message");
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult()
                            .getFieldErrors()
                            .stream()
                            .map(error -> error.getField() + ": " + error.getDefaultMessage())
                            .collect(Collectors.joining(", "));
        logger.warn("Validation error: {}", errors);
        return ResponseEntity.badRequest().body("Invalid request: " + errors);
    }
}
