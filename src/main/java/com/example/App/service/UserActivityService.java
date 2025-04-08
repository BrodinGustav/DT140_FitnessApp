package com.example.App.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.App.model.UserActivity;

@Service
public interface UserActivityService {

    UserActivity createUserActivity(UserActivity userActivity);
    List<UserActivity> getAllUserActivities();
    UserActivity getUserActivityById(Integer id);
    UserActivity updateUserActivity(Integer id, UserActivity userActivityDetails);
    void deleteUserActivity(Integer id);





}
