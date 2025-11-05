package com.example.chatclient.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank; // Import this

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    @NotBlank(message = "Sender 'from' cannot be blank")
    private String from;

    @NotBlank(message = "Message content cannot be blank")
    private String message;
}
