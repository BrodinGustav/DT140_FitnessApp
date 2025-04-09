package com.example.App.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.App.dto.CreateUserActivityDTO;
import com.example.App.dto.GetUserActivityDTO;
import com.example.App.model.UserActivity;
import com.example.App.repository.UserActivityRepository;
import com.example.App.response.SuccessResponse;
import com.example.App.service.UserActivityService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/useractivities")
public class UserActivityController {

    @Autowired
    private UserActivityRepository userActivityRepository;

    @Autowired
    private UserActivityService userActivityService;

    
    @PostMapping("/create")

    @Operation(summary = "Skapar en ny användaraktivitet", description = "Lägger till en ny användaraktivitet i databasen.")
    @ApiResponse(responseCode = "201", description = "Användaraktivitet skapades",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserActivity.class)))
    @ApiResponse(responseCode = "500", description = "Internt serverfel")

     
    public ResponseEntity<SuccessResponse<UserActivity>> createUserActivity(@RequestBody CreateUserActivityDTO userActivity) {
        UserActivity createdUserActivity = userActivityService.createUserActivity(userActivity);
        SuccessResponse<UserActivity> response = new SuccessResponse<>("Användaraktivitet har skapats", createdUserActivity);
        return ResponseEntity.ok(response);
    }


    //GET
@GetMapping 

@Operation(summary = "Hämtar användaraktiviteter", description = "Hämtar användaraktiviteter från databasen.")
@ApiResponse(responseCode = "201", description = "Användaraktiviteter hämtade",
content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserActivity.class)))
@ApiResponse(responseCode = "404", description = "Användaraktiviteter hittades inte")

public ResponseEntity<List<UserActivity>> getAllUserActivities(@RequestBody GetUserActivityDTO getUserActivity) {
    List<UserActivity> activities = userActivityService.getAllUserActivities(getUserActivity);
    return ResponseEntity.ok(activities);
}

//GET via id

@GetMapping("/{id}") 

@Operation(summary = "Hämtar specifik användaraktivitet", description = "Hämtar specifik användaraktivitet baserat på calculatedPoints.")
@ApiResponse(responseCode = "201", description = "Användaraktivitet hämtad",
content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserActivity.class)))
@ApiResponse(responseCode = "404", description = "Användaraktivitet hittades inte")

public ResponseEntity<SuccessResponse<UserActivity>> getUserActivityById(@PathVariable Integer id) {
    UserActivity userActivity = userActivityRepository.getUserActivityById(id);
    SuccessResponse<UserActivity> response = new SuccessResponse<>("Användaraktivitet med ID " + id + " har hämtats", userActivity);
    return ResponseEntity.ok(response);

}


//PUT

@PutMapping("/{id}")

@Operation(summary = "Uppdaterar användaktivitet", description = "Uppdaterar användaraktivitet i databasen.")
@ApiResponse(responseCode = "201", description = "Användaraktivitet uppdaterad",
content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserActivity.class)))   
@ApiResponse(responseCode = "404", description = "Användaraktivitet hittades inte")

public ResponseEntity<SuccessResponse<UserActivity>> updateUserActivity(@PathVariable int id, @RequestBody CreateUserActivityDTO userActivity) {
    userActivityService.createUserActivity(userActivity);
    return ResponseEntity.noContent().build();
}


//DELETE

@DeleteMapping("/{id}")

@Operation(summary = "Raderar användaraktivitet", description = "Raderar användaraktivitet från databasen.")
@ApiResponse(responseCode = "201", description = "Användaraktivitet raderad",
content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserActivity.class)))
@ApiResponse(responseCode = "404", description = "Användaraktivitet hittades inte")


public ResponseEntity<SuccessResponse<UserActivity>> deleteUserActivity(@PathVariable int id, @RequestBody UserActivity userActivity) {
    userActivity.setId(id);
    userActivityRepository.delete(userActivity);
    SuccessResponse<UserActivity> response = new SuccessResponse<>("Användaraktivitet med ID " + id + " har raderats.");
    return ResponseEntity.ok(response);
}


    

}
