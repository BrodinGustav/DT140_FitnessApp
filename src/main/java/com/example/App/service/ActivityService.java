package com.example.App.service;

import com.example.App.model.Activity;
import com.example.App.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {
    
    @Autowired
    private ActivityRepository activityRepository;

    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public Activity getActivityById(Integer id) {
        return activityRepository.findById(id).orElseThrow(() -> new RuntimeException("Activity not found"));
    }

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

}
