package com.example.App.controller;

import com.example.App.dto.UpdateUserDTO;
import com.example.App.dto.WeeklyActivityPointsDTO;
import com.example.App.model.User;
import com.example.App.response.SuccessResponse;
import com.example.App.security.SecurityContext;
import com.example.App.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Profile("!nosecurity")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/{id}")

    @Operation(summary = "Hämtar specifik användare", description = "Hämtar specifik användare baserat på id.")
    @ApiResponse(responseCode = "201", description = "Användare hämtad", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
    @ApiResponse(responseCode = "404", description = "Användaren hittades inte")

    public ResponseEntity<SuccessResponse<User>> getUser(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        SuccessResponse<User> response = new SuccessResponse<>("Användaren med ID " + id + " har hämtats", user);
        return ResponseEntity.ok(response);
    }

    @GetMapping

    @Operation(summary = "Hämtar användare", description = "Hämtar användare från användare i databasen.")
    @ApiResponse(responseCode = "201", description = "Användare hämtade", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
    @ApiResponse(responseCode = "404", description = "Användaren hittades inte")

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}/weekly-activity-points")
    public ResponseEntity<List<WeeklyActivityPointsDTO>> getWeeklyPointsByActivity(@PathVariable Integer id) {
        var result = userService.getWeeklyPointsByActivityForUser(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update")

    @Operation(summary = "Uppdaterar användare", description = "Uppdaterar användare i databasen.")
    @ApiResponse(responseCode = "201", description = "Användare uppdaterad", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
    @ApiResponse(responseCode = "404", description = "Användaren hittades inte")

    public ResponseEntity<SuccessResponse<User>> updateUser(@RequestBody @Valid UpdateUserDTO userDetails) {
        var id = SecurityContext.getThreadLocal().getId();
        userService.updateUser(id, userDetails);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")

    @Operation(summary = "Raderar användare", description = "Raderar användare från databasen.")
    @ApiResponse(responseCode = "201", description = "Användare raderad", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
    @ApiResponse(responseCode = "404", description = "Användaren hittades inte")

    public ResponseEntity<SuccessResponse<User>> deleteUser(@PathVariable int id, @RequestBody User user) {
        userService.deleteUser(id);
        SuccessResponse<User> response = new SuccessResponse<>("Användaren med ID " + id + " har raderats.");
        return ResponseEntity.ok(response);
    }

}
