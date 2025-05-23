package com.example.App.controller;

import com.example.App.dto.UpdateUserDTO;
import com.example.App.dto.WeeklyActivityPointsDTO;
import com.example.App.model.User;
import com.example.App.response.SuccessResponse;
import com.example.App.security.SecurityContext;
import com.example.App.service.UserService;
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

    @GetMapping("/{me}")

    public ResponseEntity<SuccessResponse<User>> getCurrentUser() {
        var id = SecurityContext.getThreadLocal().getId();          //Hämtar inloggad användares ID
        User user = userService.getUserById(id);                    //Hämtar användaren från databasen
        SuccessResponse<User> response = new SuccessResponse<>("Användaren med ID " + id + " har hämtats", user);
        return ResponseEntity.ok(response);
    }

    @GetMapping

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/me/weekly-activity-points")
    public ResponseEntity<List<WeeklyActivityPointsDTO>> getWeeklyPointsByActivity() {
         var id = SecurityContext.getThreadLocal().getId();
        var result = userService.getWeeklyPointsByActivityForUser(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update")

    public ResponseEntity<SuccessResponse<User>> updateUser(@RequestBody @Valid UpdateUserDTO userDetails) {
        var id = SecurityContext.getThreadLocal().getId();
        userService.updateUser(id, userDetails);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{me}")

    public ResponseEntity<SuccessResponse<User>> deleteCurrentUser() {
          var id = SecurityContext.getThreadLocal().getId();
        userService.deleteUser(id);
        SuccessResponse<User> response = new SuccessResponse<>("Användaren med ID " + id + " har raderats.");
        return ResponseEntity.ok(response);
    }

}
