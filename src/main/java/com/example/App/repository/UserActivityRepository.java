package com.example.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.App.model.UserActivity;

public interface UserActivityRepository extends JpaRepository<UserActivity, Integer> {
    UserActivity getUserActivityById(Integer id);
}
