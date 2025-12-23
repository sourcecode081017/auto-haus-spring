package com.autohaus.demo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.autohaus.demo.dto.ErrorResponseDTO;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserNotFoundException(
            UserNotFoundException ex,
            WebRequest request) {
        
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Not Found")
                .timestamp(LocalDateTime.now())
                .build();
        
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleEmailAlreadyExistsException(
            EmailAlreadyExistsException ex,
            WebRequest request) {
        
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .message(ex.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .error("Conflict")
                .timestamp(LocalDateTime.now())
                .build();
        
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGlobalException(
            Exception ex,
            WebRequest request) {
        
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .message("An unexpected error occurred")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Internal Server Error")
                .timestamp(LocalDateTime.now())
                .build();
        
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
