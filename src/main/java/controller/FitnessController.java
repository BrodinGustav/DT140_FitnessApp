package controller;

import service.FitnessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fitness")
public class FitnessController {

    @Autowired
    private FitnessService fitnessService;

    @PostMapping("/addActivityForUser")
    public void addActivityForUser(@RequestParam Integer userId, @RequestParam Integer activityId, @RequestParam int points) {
        fitnessService.addActivityForUser(userId, activityId, points);
    }
}
