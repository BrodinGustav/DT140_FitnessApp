package com.example.App.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.App.dto.CreateUserActivityDTO;
import com.example.App.dto.GetUserActivityDTO;
import com.example.App.model.UserActivity;

@Service
public interface UserActivityService {

    UserActivity createUserActivity(CreateUserActivityDTO userActivity);
    List<UserActivity> getAllUserActivities(GetUserActivityDTO getUserAllActivity);
    UserActivity getUserActivityById(Integer id);
   // UserActivity updateUserActivity(Integer id, CreateUserActivityDTO userActivity);
    void deleteUserActivity(Integer id);





}
