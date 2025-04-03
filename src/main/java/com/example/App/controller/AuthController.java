package com.example.App.controller;

import com.example.App.model.User;
import com.example.App.model.LoginCredentials;
import com.example.App.repository.UserRepository;
import com.example.App.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController 
@RequestMapping("/api/auth") 
public class AuthController {

    //Injecting Dependencies
    @Autowired private UserRepository userRepository;
    @Autowired private JWTUtil jwtUtil;
    @Autowired private AuthenticationManager authManager;
    @Autowired private PasswordEncoder passwordEncoder;

    //POST för register
    @PostMapping("/register")
    public Map<String, Object> registerHandler(@RequestBody User user){
        
        // Encoding Password using Bcrypt
        String encodedPass = passwordEncoder.encode(user.getPassword());

        // Setting the encoded password
        user.setPassword(encodedPass);

        // Persisting the User Entity to H2 Database
        user = userRepository.save(user);

        // Generating JWT
        String token = jwtUtil.generateToken(user.getEmail());

        // Responding with JWT
        return Collections.singletonMap("jwt-token", token);
    }

    // Defining the function to handle the POST route for logging in a user
    @PostMapping("/login")
    public Map<String, Object> loginHandler(@RequestBody LoginCredentials body){
        try {
            // Creating the Authentication Token which will contain the credentials for authenticating
            // This token is used as input to the authentication process
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());

            // Authenticating the Login Credentials
            authManager.authenticate(authInputToken);

            // If this point is reached it means Authentication was successful
            // Generate the JWT
            String token = jwtUtil.generateToken(body.getEmail());

            // Respond with the JWT
            return Collections.singletonMap("jwt-token", token);
        }catch (AuthenticationException authExc){
            // Auhentication Failed
            throw new RuntimeException("Invalid Login Credentials");
        }
    }
    


}

