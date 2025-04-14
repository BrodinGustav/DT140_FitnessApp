package com.example.App.dto;

import java.util.Optional;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Pattern;

@Validated
public class UpdateUserDTO {
    private String name;
    @Pattern(regexp = ".*@.*")
    private String email;

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }

    
}
