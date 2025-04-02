package com.example.App.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.App.dto.UserActivityDTO;
import com.example.App.model.UserActivity;
import com.example.App.service.FitnessService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/fitness")
public class FitnessController {

    @Autowired
    private FitnessService fitnessService;

    @PostMapping("/addActivityForUser")

    @Operation(summary = "Skapar en ny användaraktivitet", description = "Lägger till en ny användaraktivitet i databasen.")
    @ApiResponse(responseCode = "201", description = "Användaraktivitet skapades",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserActivity.class)))
    @ApiResponse(responseCode = "500", description = "Internt serverfel")

    public void addActivityForUser(@RequestBody UserActivityDTO userActivityDTO) {
        fitnessService.addActivityForUser(userActivityDTO);
    }

    @GetMapping("/userActivities")

    @Operation(summary = "Hämtar användaraktivitet", description = "Hämtar användaraktivitet från databasen.")
    @ApiResponse(responseCode = "201", description = "Användaraktivitet hämtade",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserActivity.class)))
    @ApiResponse(responseCode = "404", description = "Användaraktivitet hittades inte")

    public List<UserActivityDTO> getUserActivities(@RequestParam Integer userId) {
        return fitnessService.getUserActivities(userId);
    }





    
    @Override
    public String toString() {
        return "FitnessController [fitnessService=" + fitnessService + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fitnessService == null) ? 0 : fitnessService.hashCode());
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
        FitnessController other = (FitnessController) obj;
        if (fitnessService == null) {
            if (other.fitnessService != null)
                return false;
        } else if (!fitnessService.equals(other.fitnessService))
            return false;
        return true;
    }

    

}
