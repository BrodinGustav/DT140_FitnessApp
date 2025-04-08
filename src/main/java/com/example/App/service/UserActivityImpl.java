package com.example.App.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.App.execption.ResourceNotFoundException;
import com.example.App.model.UserActivity;
import com.example.App.repository.UserActivityRepository;


public class UserActivityImpl implements UserActivityService {
    
 @Autowired
    private UserActivityRepository userActivityRepository;

    @Override
    public UserActivity createUserActivity(UserActivity userActivity) {
        return userActivityRepository.save(userActivity);   //Fungerar även som update och create
    }

    @Override
    public List<UserActivity> getAllUserActivities() {
        return userActivityRepository.findAll();
    }
    
    @Override
    public UserActivity getUserActivityById(Integer id) {
        return userActivityRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("UserActivity med id " + id + " hittades inte."));
    }

    @Override
     public UserActivity updateUserActivity(Integer id, UserActivity userActivityDetails) {
        UserActivity userActivity = userActivityRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("UserActivity med id " + id + " hittades inte."));
        
        userActivity.setPoints(userActivityDetails.getPoints());
        
        return userActivityRepository.save(userActivity);
    }

    @Override
    public void deleteUserActivity(Integer id) {
        userActivityRepository.deleteById(id);
    }


}
