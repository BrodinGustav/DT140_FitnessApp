package com.example.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.App.model.Activity;


public interface ActivityRepository extends JpaRepository<Activity, Integer> {
     Activity getActivityById(Integer id);
}

