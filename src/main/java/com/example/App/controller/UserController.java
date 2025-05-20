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

    @GetMapping("/{id}")

    public ResponseEntity<SuccessResponse<User>> getUser(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        SuccessResponse<User> response = new SuccessResponse<>("Användaren med ID " + id + " har hämtats", user);
        return ResponseEntity.ok(response);
    }

    @GetMapping

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}/weekly-activity-points")
    public ResponseEntity<List<WeeklyActivityPointsDTO>> getWeeklyPointsByActivity(@PathVariable Integer id) {
        var result = userService.getWeeklyPointsByActivityForUser(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update")

    public ResponseEntity<SuccessResponse<User>> updateUser(@RequestBody @Valid UpdateUserDTO userDetails) {
        var id = SecurityContext.getThreadLocal().getId();
        userService.updateUser(id, userDetails);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<SuccessResponse<User>> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        SuccessResponse<User> response = new SuccessResponse<>("Användaren med ID " + id + " har raderats.");
        return ResponseEntity.ok(response);
    }

}
