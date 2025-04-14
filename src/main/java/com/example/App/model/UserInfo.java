package com.example.App.model;

public class UserInfo {
    private Integer id;
    private String email;
    private String name;
    public UserInfo(Integer id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }
    public Integer getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }

    
}
