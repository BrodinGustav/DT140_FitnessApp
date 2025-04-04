package com.example.App.execption;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;

@ControllerAdvice
    public class GlobalExceptionHandler {
        private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<CustomErrorResponse> handleNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
            logger.error("Hittade inte resurs: {}", ex.getMessage());

            CustomErrorResponse error = new CustomErrorResponse(
                HttpStatus.NOT_FOUND.value(),
               ex.getMessage(),
            LocalDateTime.now(),
            request.getRequestURI(),
            "RESOURCE_NOT_FOUND"
            );
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    
        @ExceptionHandler({IllegalArgumentException.class, ValidationException.class})
        public ResponseEntity<CustomErrorResponse> handleValidation(Exception ex, HttpServletRequest request) {
            logger.error("Valideringsfel: {}", ex.getMessage());

            CustomErrorResponse error = new CustomErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                 ex.getMessage(),
            LocalDateTime.now(),
            request.getRequestURI(),
            "VALIDATION_ERROR"
            );

            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }