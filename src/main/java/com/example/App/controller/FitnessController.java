package com.example.App.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.App.dto.UserActivityDTO;
import com.example.App.service.FitnessService;

@RestController
@RequestMapping("/api/fitness")
public class FitnessController {

    @Autowired
    private FitnessService fitnessService;

    @PostMapping("/addActivityForUser")
    public void addActivityForUser(@RequestBody UserActivityDTO userActivityDTO) {
        fitnessService.addActivityForUser(userActivityDTO);
    }

    @GetMapping("/userActivities")
    public List<UserActivityDTO> getUserActivities(@RequestParam Integer userId) {
        return fitnessService.getUserActivities(userId);
    }
}
