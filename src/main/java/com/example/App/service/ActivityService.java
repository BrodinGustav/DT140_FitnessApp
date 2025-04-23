package com.example.App.service;


import java.util.Arrays;
import java.util.List;


import org.springframework.stereotype.Service;

import com.example.App.dto.ActivityDTO;
import com.example.App.model.Activity;

@Service
public class ActivityService {               

    public static List<ActivityDTO> getAllActivities() {
        return Arrays.stream(Activity.values())
        .map(activity -> new ActivityDTO(activity.name(), activity.value))
        .toList();
    }

  
}
