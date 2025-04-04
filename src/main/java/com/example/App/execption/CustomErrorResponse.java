package com.example.App.execption;

import java.time.LocalDateTime;


public class CustomErrorResponse {

        private int status;
        private String message;
        private LocalDateTime timestamp;
        private String path;
        private String errorCode;


        // Konstruktor

        public CustomErrorResponse(int status, String message, LocalDateTime timestamp, String path, String errorCode) {
            this.status = status;
            this.message = message;
            this.timestamp = timestamp;
            this.path = path;
            this.errorCode = errorCode;
        }

        // Getters/Setters

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

    }

