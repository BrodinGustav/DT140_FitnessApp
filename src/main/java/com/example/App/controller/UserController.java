package com.example.App.controller;

import com.example.App.model.User;
import com.example.App.repository.UserRepository;
import com.example.App.response.SuccessResponse;
import com.example.App.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired private UserService userService;
    @Autowired private UserRepository userRepository;


    @PostMapping("/create") //Behövs denna nu när Register finns? 
 
     @Operation(summary = "Skapar en ny användare", description = "Lägger till en ny användare i databasen.")
    @ApiResponse(responseCode = "201", description = "Användare skapad",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
    @ApiResponse(responseCode = "500", description = "Internt serverfel")

    public ResponseEntity<SuccessResponse<User>> createUser(@RequestBody User user) {
         userService.createUser(user);
         SuccessResponse<User> response = new SuccessResponse<>("Användare har skapats", user);
         return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")

    @Operation(summary = "Hämtar specifik användare", description = "Hämtar specifik användare baserat på id.")
    @ApiResponse(responseCode = "201", description = "Användare hämtad",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
    @ApiResponse(responseCode = "404", description = "Användaren hittades inte")

    public ResponseEntity<SuccessResponse<User>> getUser(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        SuccessResponse<User> response = new SuccessResponse<>("Användaren med ID " + id + " har hämtats", user);
        return ResponseEntity.ok(response);
    }

    
    @GetMapping

    @Operation(summary = "Hämtar användare", description = "Hämtar användare från användare i databasen.")
    @ApiResponse(responseCode = "201", description = "Användare hämtade",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
    @ApiResponse(responseCode = "404", description = "Användaren hittades inte")


   public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = userService.getAllUsers();
    return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")  

    @Operation(summary = "Uppdaterar användare", description = "Uppdaterar användare i databasen.")
    @ApiResponse(responseCode = "201", description = "Användare uppdaterad",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))   
    @ApiResponse(responseCode = "404", description = "Användaren hittades inte")

    public ResponseEntity<SuccessResponse<User>> updateUser(@PathVariable Integer id, @RequestBody User userDetails) {
        userService.updateUser(id, userDetails);
        SuccessResponse<User> response = new SuccessResponse<>("Användaren med ID " + id + " har uppdaterats", userDetails);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}") 

    @Operation(summary = "Raderar användare", description = "Raderar användare från databasen.")
    @ApiResponse(responseCode = "201", description = "Användare raderad",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
    @ApiResponse(responseCode = "404", description = "Användaren hittades inte")
    
    public ResponseEntity<SuccessResponse<User>> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        SuccessResponse<User> response = new SuccessResponse<>("Användaren med ID " + id + " har raderats.");
        return ResponseEntity.ok(response);
}

    //Säker rutt för USER_ROLE
    @GetMapping("/info")
    public User getUserDetails(){
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(email).get();
    }




 }

