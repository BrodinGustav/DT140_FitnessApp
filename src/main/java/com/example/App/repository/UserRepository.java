package com.example.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.App.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
