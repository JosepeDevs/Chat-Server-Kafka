package com.example.chatclient.adapter.inbound.rest.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @Mock
    private WebRequest webRequest;

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void handleGenericException_shouldReturnInternalServerError() {
        // Given
        Exception genericException = new RuntimeException("Some generic error");

        // When
        ResponseEntity<ErrorResponse> responseEntity =
            globalExceptionHandler.handleGenericException(genericException, webRequest);

        // Then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("INTERNAL_SERVER_ERROR", responseEntity.getBody().getErrorCode());
        assertEquals("An unexpected error occurred. Please try again later.", responseEntity.getBody().getMessage());
    }

    @Test
    void handleValidationExceptions_shouldReturnBadRequest() {
        // Given
        // Mocking the structure of MethodArgumentNotValidException
        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(Collections.singletonList(new FieldError("objectName", "fieldName", "defaultMessage")));


        // When
        ResponseEntity<ErrorResponse> responseEntity =
            globalExceptionHandler.handleValidationExceptions(methodArgumentNotValidException, webRequest);

        // Then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("VALIDATION_ERROR", responseEntity.getBody().getErrorCode());
        assertEquals("Invalid request payload. Please check your input.", responseEntity.getBody().getMessage());
    }
}
