package com.example.App.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.App.dto.UserActivityDTO;
import com.example.App.model.Activity;
import com.example.App.model.User;
import com.example.App.model.UserActivity;
import com.example.App.repository.ActivityRepository;
import com.example.App.repository.UserActivityRepository;
import com.example.App.repository.UserRepository;

@Service
public class UserActivityService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserActivityRepository userActivityRepository;

    //Skapa UserActivity
    public void addActivityForUser(UserActivityDTO userActivityDTO) {
        User user = userRepository.findById(userActivityDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Användare hittades ej"));
        Activity activity = activityRepository.findById(userActivityDTO.getActivityId())
                .orElseThrow(() -> new RuntimeException("Aktivitet hittades ej"));

        UserActivity userActivity = new UserActivity();
        userActivity.setUser(user);
        userActivity.setActivity(activity);
        userActivity.setPoints(userActivityDTO.getPoints());

        userActivityRepository.save(userActivity);
    }


    //Hämta UserActivity via ID
    public List<UserActivityDTO> getUserActivities(Integer userId) {
        List<UserActivity> userActivities = userActivityRepository.findByUserId(userId);

        // debugg
        System.out.println("Antal aktiviteter hittade för userId " + userId + ": " + userActivities.size());

        return userActivities.stream().map(userActivity -> {
            UserActivityDTO dto = new UserActivityDTO();
            dto.setId(userActivity.getId());
            dto.setUserId(userActivity.getUser().getId());
            dto.setActivityId(userActivity.getActivity().getId());
            dto.setPoints(userActivity.getPoints());

            // Hämta användarens namn
            String userName = userActivity.getUser().getName();
            dto.setUserName(userName); 

            // Hämta aktivitetens namn
            String activityName = userActivity.getActivity().getName(); 
            dto.setActivityName(activityName); 

            // Hämta category
            String category = userActivity.getActivity().getCategory().getName(); 
            dto.setCategory(category);

            return dto;
        }).collect(Collectors.toList());
    }
}
