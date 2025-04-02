package controller;

import service.FitnessService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import dto.UserActivityDTO;

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
