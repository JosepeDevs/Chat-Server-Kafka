package com.example.chatclient.domain.model;

import javax.validation.constraints.NotBlank; // Import this

public class ChatMessage {

    @NotBlank(message = "Sender 'from' cannot be blank")
    private String from;

    @NotBlank(message = "Message content cannot be blank")
    private String message;

    // Constructors
    public ChatMessage() {
    }

    public ChatMessage(String from, String message) {
        this.from = from;
        this.message = message;
    }

    // Getters and Setters
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // toString() method
    @Override
    public String toString() {
        return "ChatMessage{" +
                "from='" + from + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
