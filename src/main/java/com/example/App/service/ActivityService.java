package com.example.App.service;

import com.example.App.execption.ResourceNotFoundException;
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
        return activityRepository.findById(id)
         .orElseThrow(() -> new ResourceNotFoundException("Aktivitet med id " + id + " hittades inte."));
    }

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public Activity updateActivity(Integer id, Activity activityDetails) {
        Activity activity = activityRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Aktivitet med id " + id + " hittades inte."));

        activity.setName(activityDetails.getName());
        activity.setPoints(activityDetails.getPoints());
        return activityRepository.save(activity);
    }

    public void deleteActivity(Integer id) {
        activityRepository.deleteById(id);
    }

}
