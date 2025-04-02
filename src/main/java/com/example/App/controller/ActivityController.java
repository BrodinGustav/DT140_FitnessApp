package com.example.App.controller;

import com.example.App.model.Activity;
import com.example.App.service.ActivityService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {
    
    @Autowired
    private ActivityService activityService;

    @PostMapping("/create")

        @Operation(summary = "Skapar en ny aktivitet", description = "Lägger till en ny aktivitet i databasen.")
    @ApiResponse(responseCode = "201", description = "Aktivitet skapad",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Activity.class)))
    @ApiResponse(responseCode = "500", description = "Internt serverfel")

    public Activity createActivity(@RequestBody Activity activity) {
        return activityService.createActivity(activity);
    }

    @GetMapping("/{id}")

    @Operation(summary = "Hämtar specifik aktivitet", description = "Hämtar specifik aktivitet baserat på id.")
    @ApiResponse(responseCode = "201", description = "Aktivitet hämtad",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Activity.class)))
    @ApiResponse(responseCode = "404", description = "Aktivitet hittades inte")

    public Activity getActivity(@PathVariable Integer id) {
        return activityService.getActivityById(id);
    }

    @GetMapping

    @Operation(summary = "Hämtar aktivitet", description = "Hämtar aktivitet fråndatabasen.")
    @ApiResponse(responseCode = "201", description = "Aktivitet hämtad",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Activity.class)))
    @ApiResponse(responseCode = "404", description = "Aktivitet hittades inte")

    public List<Activity> getAllActivities() {
        return activityService.getAllActivities();
    }

        @PutMapping("/{id}")

        @Operation(summary = "Uppdaterar aktivitet", description = "Uppdaterar aktivitet i databasen.")
        @ApiResponse(responseCode = "201", description = "Aktivitet uppdaterad",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Activity.class)))   
        @ApiResponse(responseCode = "404", description = "Aktivitet hittades inte")

        public Activity updateActivity(@PathVariable Integer id, @RequestBody Activity activityDetails) {
        return activityService.updateActivity(id, activityDetails);
    }

    @DeleteMapping("/{id}")

    @Operation(summary = "Raderar aktivitet", description = "Raderar aktivitet från databasen.")
    @ApiResponse(responseCode = "201", description = "Aktivitet raderad",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Activity.class)))
    @ApiResponse(responseCode = "404", description = "Aktivitet hittades inte")

    public void deleteActivity(@PathVariable Integer id) {
    activityService.deleteActivity(id);
}
}

