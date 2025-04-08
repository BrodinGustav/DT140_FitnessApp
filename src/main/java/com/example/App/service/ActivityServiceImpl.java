package com.example.App.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.App.execption.ResourceNotFoundException;
import com.example.App.model.Activity;
import com.example.App.repository.ActivityRepository;

@Service
public class ActivityServiceImpl implements ActivityService{

     @Autowired
    private ActivityRepository activityRepository;

    @Override
    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public Activity getActivityById(Integer id) {
        return activityRepository.findById(id)
         .orElseThrow(() -> new ResourceNotFoundException("Aktivitet med id " + id + " hittades inte."));
    }

    @Override
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    @Override
    public Activity updateActivity(Integer id, Activity activityDetails) {
        Activity activity = activityRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Aktivitet med id " + id + " hittades inte."));

        activity.setName(activityDetails.getName());
        activity.setPoints(activityDetails.getPoints());
        return activityRepository.save(activity);
    }

    @Override
    public void deleteActivity(Integer id) {
        activityRepository.deleteById(id);
    }

}
