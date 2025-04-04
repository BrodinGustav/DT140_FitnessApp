package com.example.App.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.App.dto.UserActivityDTO;
import com.example.App.model.UserActivity;
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

    public void addActivityForUser(@RequestBody UserActivityDTO userActivityDTO) {
        userActivityService.addActivityForUser(userActivityDTO);
    }

    @GetMapping("/{id}")

    @Operation(summary = "Hämtar användaraktivitet", description = "Hämtar användaraktivitet från databasen.")
    @ApiResponse(responseCode = "201", description = "Användaraktivitet hämtade",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserActivity.class)))
    @ApiResponse(responseCode = "404", description = "Användaraktivitet hittades inte")

    public List<UserActivityDTO> getUserActivities(@RequestParam Integer userId) {
        return userActivityService.getUserActivities(userId);
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
