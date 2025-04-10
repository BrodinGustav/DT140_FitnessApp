package com.example.App.controller;

import com.example.App.model.User;
import com.example.App.dto.RegisterDTO;
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
    public RegisterDTO registerHandler(@RequestBody User user){
        
        // Encoding Password med Bcrypt
        String encodedPass = passwordEncoder.encode(user.getPassword());

        // Sätter det encoded password
        user.setPassword(encodedPass);

        //Spara användare
        user = userRepository.save(user);

        // Genererar JWT
        String token = jwtUtil.generateToken(user.getEmail());

         // Returnera namn, email och token
        return new RegisterDTO(user.getName(), user.getEmail(), token);

       /*  Svarar with JWT
        return Collections.singletonMap("jwt-token", token);
        */
        }

    // Definierar funktion som hanterar POST rutt för att logga in en user
    @PostMapping("/login")
    public Map<String, Object> loginHandler(@RequestBody LoginCredentials body){
        try {
            //Skapar en Authentication Token som innehåller credentials för authenticating
            // Token används som input till authentication process
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());

            // Authenticating the Login Credentials
            authManager.authenticate(authInputToken);

            // Om Authentication lyckades
            // Generera JWT
            String token = jwtUtil.generateToken(body.getEmail());

            // Svara med JWT
            return Collections.singletonMap("jwt-token", token);
        }catch (AuthenticationException authExc){
            // Auhentication Failed
            throw new RuntimeException("Invalid Login Credentials");
        }
    }
    


}

