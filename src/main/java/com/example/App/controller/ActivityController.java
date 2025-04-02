package com.example.App.controller;

import com.example.App.model.Activity;
import com.example.App.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActivityController {
    
    @Autowired
    private ActivityService activityService;

    @PostMapping("/create")
    public Activity createActivity(@RequestBody Activity activity) {
        return activityService.createActivity(activity);
    }

    @GetMapping("/{id}")
    public Activity getActivity(@PathVariable Integer id) {
        return activityService.getActivityById(id);
    }

    @GetMapping
    public List<Activity> getAllActivities() {
        return activityService.getAllActivities();
    }
}

