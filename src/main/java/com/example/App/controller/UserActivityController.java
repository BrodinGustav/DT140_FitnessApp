package com.example.App.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.App.dto.UserActivityDTO;
import com.example.App.model.User;
import com.example.App.model.UserActivity;
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
    private UserActivityService userActivityService;

    
    @PostMapping("/create")

    @Operation(summary = "Skapar en ny användaraktivitet", description = "Lägger till en ny användaraktivitet i databasen.")
    @ApiResponse(responseCode = "201", description = "Användaraktivitet skapades",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserActivity.class)))
    @ApiResponse(responseCode = "500", description = "Internt serverfel")

     
    public ResponseEntity<SuccessResponse<UserActivity>> addActivityForUser(@RequestBody UserActivity userActivity) {
        userActivityService.saveUserActivity(userActivity);
        SuccessResponse<UserActivity> response = new SuccessResponse<>("Användaraktivitet har skapats", userActivity);
        return ResponseEntity.ok(response);
    }


    //GET
@GetMapping 

@Operation(summary = "Hämtar användaraktiviteter", description = "Hämtar användaraktiviteter från databasen.")
@ApiResponse(responseCode = "201", description = "Användaraktiviteter hämtade",
content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserActivity.class)))
@ApiResponse(responseCode = "404", description = "Användaraktiviteter hittades inte")

public ResponseEntity<List<UserActivity>> getAllUserActivities() {
    List<UserActivity> userActivities = userActivityService.getAllUserActivities();

    if (userActivities.isEmpty()) {
        return ResponseEntity.noContent().build(); //204 No Content om listan är tom
    }

    
    System.out.println(userActivities);
    userActivities.forEach(System.out::println);
     

    return ResponseEntity.ok(userActivities); //200 OK om det finns användare
}


//GET via id

@GetMapping("/{id}") 

@Operation(summary = "Hämtar specifik användaraktivitet", description = "Hämtar specifik användaraktivitet baserat på calculatedPoints.")
@ApiResponse(responseCode = "201", description = "Användaraktivitet hämtad",
content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserActivity.class)))
@ApiResponse(responseCode = "404", description = "Användaraktivitet hittades inte")

public ResponseEntity<SuccessResponse<UserActivity>> getUserActivityById(@PathVariable Integer id) {
    UserActivity userActivity = userActivityService.getUserActivityById(id);
    SuccessResponse<UserActivity> response = new SuccessResponse<>("Användaraktivitet med ID " + id + " har hämtats", userActivity);
    return ResponseEntity.ok(response);

}


//PUT

@PutMapping("/{id}")

@Operation(summary = "Uppdaterar användaktivitet", description = "Uppdaterar användaraktivitet i databasen.")
@ApiResponse(responseCode = "201", description = "Användaraktivitet uppdaterad",
content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserActivity.class)))   
@ApiResponse(responseCode = "404", description = "Användaraktivitet hittades inte")

public ResponseEntity<SuccessResponse<UserActivity>> updateUserActivity(@PathVariable int id, @RequestBody UserActivity userActivity) {
    userActivity.setId(id);
    SuccessResponse<UserActivity> response = new SuccessResponse<>("Användaraktivitet med ID " + id + " har uppdaterats", userActivity);
    return ResponseEntity.ok(response);
}


//DELETE

@DeleteMapping("/{id}")

@Operation(summary = "Raderar användaraktivitet", description = "Raderar användaraktivitet från databasen.")
@ApiResponse(responseCode = "201", description = "Användaraktivitet raderad",
content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserActivity.class)))
@ApiResponse(responseCode = "404", description = "Användaraktivitet hittades inte")


public ResponseEntity<SuccessResponse<UserActivity>> deleteUserActivity(@PathVariable int id, @RequestBody UserActivity userActivity) {
    userActivity.setId(id);
    userActivityService.deleteUserActivity(id);
    SuccessResponse<UserActivity> response = new SuccessResponse<>("Användaraktivitet med ID " + id + " har raderats.");
    return ResponseEntity.ok(response);
}


    




    
    @Override
    public String toString() {
        return "UserActivityController [userActivityService=" + userActivityService + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userActivityService == null) ? 0 : userActivityService.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserActivityController other = (UserActivityController) obj;
        if (userActivityService == null) {
            if (other.userActivityService != null)
                return false;
        } else if (!userActivityService.equals(other.userActivityService))
            return false;
        return true;
    }

    

}
