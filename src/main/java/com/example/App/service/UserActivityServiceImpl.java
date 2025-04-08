package com.example.App.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.App.dto.CreateUserActivityDTO;
import com.example.App.execption.ResourceNotFoundException;
import com.example.App.model.UserActivity;
import com.example.App.repository.ActivityRepository;
import com.example.App.repository.UserActivityRepository;
import com.example.App.repository.UserRepository;


@Service
public class UserActivityServiceImpl implements UserActivityService {

    @Autowired
    private ActivityRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;
    
 @Autowired
    private UserActivityRepository userActivityRepository;
    

    @Override
    public UserActivity createUserActivity(CreateUserActivityDTO putUserActivity) {

        var category = categoryRepository.getActivityByName(putUserActivity.getActivityName());
        var user = userRepository.findById(putUserActivity.getUserId())
        .orElseThrow();

        var userActivity = new UserActivity();
        userActivity.setUser(user);
        userActivity.setActivity(category);
        userActivity.setPoints(100);
        userActivity.setActivity(category);

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
