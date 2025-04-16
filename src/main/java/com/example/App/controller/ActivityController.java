package com.example.App.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.App.dto.ActivityDTO;
import com.example.App.service.ActivityService;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/activities")
@CrossOrigin(origins = "http://localhost:4200")

public class ActivityController {

   private final ActivityService activityService;
 
   public ActivityController(ActivityService activityService) {
      this.activityService = activityService;
   }

   @GetMapping
   public ResponseEntity<List<ActivityDTO>> getAllActivities() {
       return ResponseEntity.ok(activityService.getAllActivities());
   }
}
