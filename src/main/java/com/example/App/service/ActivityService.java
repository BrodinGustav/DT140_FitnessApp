package com.example.App.service;

import com.example.App.model.Activity;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ActivityService {
    Activity createActivity(Activity activity);
    Activity  getActivityById(Integer id);
    List<Activity>getAllActivities();
    Activity updateActivity(Integer id, Activity activityDetails);
    void deleteActivity(Integer id);
   
}