package com.example.chatclient.adapter.inbound.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.stream.Collectors; // For more detailed validation errors, if chosen

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
        // Log the exception for server-side review
        // logger.error("Unexpected error occurred", ex); // Assuming a logger is available
        ErrorResponse errorResponse = new ErrorResponse("INTERNAL_SERVER_ERROR", "An unexpected error occurred. Please try again later.");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        // Example of extracting more detailed errors:
        // String errors = ex.getBindingResult().getFieldErrors().stream()
        //                   .map(error -> error.getField() + ": " + error.getDefaultMessage())
        //                   .collect(Collectors.joining(", "));
        // ErrorResponse errorResponse = new ErrorResponse("VALIDATION_ERROR", "Invalid request: " + errors);

        ErrorResponse errorResponse = new ErrorResponse("VALIDATION_ERROR", "Invalid request payload. Please check your input.");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Consider adding handlers for other common exceptions like:
    // @ExceptionHandler(IllegalArgumentException.class)
    // public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
    //     ErrorResponse errorResponse = new ErrorResponse("ILLEGAL_ARGUMENT", ex.getMessage());
    //     return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    // }

    // Example for a custom business exception:
    // @ExceptionHandler(CustomBusinessException.class)
    // public ResponseEntity<ErrorResponse> handleCustomBusinessException(CustomBusinessException ex, WebRequest request) {
    //     ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), ex.getMessage());
    //     return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    // }
}
