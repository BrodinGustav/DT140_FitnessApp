package com.example.App.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.App.model.UserActivity;

public interface UserActivityRepository extends JpaRepository<UserActivity, Integer> {
    List<UserActivity> findByUserId(Integer userId);
}
