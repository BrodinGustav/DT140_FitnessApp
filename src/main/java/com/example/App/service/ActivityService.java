package com.example.App.service;


import java.util.Arrays;
import java.util.List;


import org.springframework.stereotype.Service;

import com.example.App.dto.ActivityDTO;
import com.example.App.model.Activity;

@Service
public class ActivityService {               

    public List<ActivityDTO> getAllActivities() {

        //Hämtar alla värden i enum Activity
        return Arrays.stream(Activity.values())

        //Konverterar varje Activity-objekt till ett ActivityDTO
        .map(activity -> new ActivityDTO(activity.name(), activity.value))

        //Listar alla ActivityDTO
        .toList();
    }

  
}
