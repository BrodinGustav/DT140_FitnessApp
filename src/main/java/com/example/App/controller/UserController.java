package com.example.App.controller;

import com.example.App.model.User;
import com.example.App.repository.UserRepository;
import com.example.App.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
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

    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")

    @Operation(summary = "Hämtar specifik användare", description = "Hämtar specifik användare baserat på id.")
    @ApiResponse(responseCode = "201", description = "Användare hämtad",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
    @ApiResponse(responseCode = "404", description = "Användaren hittades inte")

    public User getUser(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    
    @GetMapping

    @Operation(summary = "Hämtar användare", description = "Hämtar användare från användare i databasen.")
    @ApiResponse(responseCode = "201", description = "Användare hämtade",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
    @ApiResponse(responseCode = "404", description = "Användaren hittades inte")


    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/api/user/{id}")  

    @Operation(summary = "Uppdaterar användare", description = "Uppdaterar användare i databasen.")
    @ApiResponse(responseCode = "201", description = "Användare uppdaterad",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))   
    @ApiResponse(responseCode = "404", description = "Användaren hittades inte")

    public User updateUser(@PathVariable Integer id, @RequestBody User userDetails) {
        return userService.updateUser(id, userDetails);
    }

    @DeleteMapping("/api/user/{id}") 

    @Operation(summary = "Raderar användare", description = "Raderar användare från databasen.")
    @ApiResponse(responseCode = "201", description = "Användare raderad",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
    @ApiResponse(responseCode = "404", description = "Användaren hittades inte")
    
    public void deleteUser(@PathVariable Integer id) {
    userService.deleteUser(id);
}

    //Säker rutt för USER_ROLE
    @GetMapping("/info")
    public User getUserDetails(){
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(email).get();
    }




 }

