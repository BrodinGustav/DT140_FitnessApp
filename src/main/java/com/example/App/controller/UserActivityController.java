package com.example.App.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.example.App.dto.CreateUserActivityDTO;
import com.example.App.dto.LeaderboardDTO;
import com.example.App.model.UserActivity;
import com.example.App.repository.UserActivityRepository;
import com.example.App.response.SuccessResponse;
import com.example.App.security.SecurityContext;
import com.example.App.service.UserActivityService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/useractivities")
@CrossOrigin(origins = "http://localhost:4200")
@Transactional
public class UserActivityController {

    @Autowired
    private UserActivityRepository userActivityRepository;

    @Autowired
    private UserActivityService userActivityService;

    @PostMapping("/create")

    @Transactional
    public ResponseEntity<SuccessResponse<UserActivity>> createUserActivity(
            @RequestBody @Valid CreateUserActivityDTO userActivity) {
        userActivityService.createUserActivity(userActivity);
        return ResponseEntity.noContent().build();
    }

    // GET
    @GetMapping

    public ResponseEntity<List<UserActivity>> getAllUserActivities() {
        List<UserActivity> activities = userActivityRepository.findAll();
        return ResponseEntity.ok(activities);
    }

    // GET för poäng till LeaderBoard
    @GetMapping("/stream")
    @Transactional(readOnly = true)
    public ResponseEntity<List<LeaderboardDTO>> totalResultat() {
        List<LeaderboardDTO> resultat = userActivityService.totalResultat();
        return ResponseEntity.ok(resultat);
    }

    // GET via id

    @GetMapping("/{me}")

    public ResponseEntity<SuccessResponse<UserActivity>> getUserActivityById() {
         var id = SecurityContext.getThreadLocal().getId();
        UserActivity userActivity = userActivityRepository.getUserActivityById(id);
        SuccessResponse<UserActivity> response = new SuccessResponse<>(
                "Användaraktivitet med ID " + id + " har hämtats", userActivity);
        return ResponseEntity.ok(response);

    }

    // PUT

    @PutMapping("/{id}")

    public ResponseEntity<SuccessResponse<UserActivity>> updateUserActivity(@PathVariable int id,
            @RequestBody CreateUserActivityDTO userActivity) {
        userActivityService.createUserActivity(userActivity);
        return ResponseEntity.noContent().build();
    }

    // DELETE

    @DeleteMapping("/{id}")

    public ResponseEntity<SuccessResponse<UserActivity>> deleteUserActivity(@PathVariable int id) {
        userActivityService.deleteUserActivity(id);
        SuccessResponse<UserActivity> response = new SuccessResponse<>(
                "Användaraktivitet med ID " + id + " har raderats.");
        return ResponseEntity.ok(response);
    }

}
