package com.example.App.response;

import java.time.LocalDateTime;

public class SuccessResponse<T> {
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public SuccessResponse(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }


    //Konstruktor

    public SuccessResponse(String message, T data) {
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }


// getters & setters

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    
    
    
}
