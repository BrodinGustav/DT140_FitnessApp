package com.example.App.service;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.App.dto.ActivityDTO;
import com.example.App.model.Activity;

@Service
public class ActivityService {

    public List<ActivityDTO> getAllActivities() {
        return Arrays.stream(Activity.values())
        .map(activity -> new ActivityDTO(activity.name()))
        .collect(Collectors.toList());
    }
    
    
}
