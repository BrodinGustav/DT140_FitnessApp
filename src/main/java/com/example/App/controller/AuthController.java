package com.example.App.controller;

import com.example.App.model.User;
import com.example.App.dto.RegisterDTO;
import com.example.App.model.LoginCredentials;
import com.example.App.repository.UserRepository;
import com.example.App.security.JWTUtil;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Profile("!nosecurity")
public class AuthController {

    // Injecting Dependencies
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //Registrering
    @PostMapping("/register")
    public ResponseEntity<RegisterDTO> registerHandler(@RequestBody @Valid User user) {

        try {

            // Kontroll om användare redan finns
            if (userRepository.existsByEmail(user.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new RegisterDTO("Användare finns redan.", "", ""));
            }

            // Encoding Password med Bcrypt
            String encodedPass = passwordEncoder.encode(user.getPassword());

            // Sätter det encoded password
            user.setPassword(encodedPass);

            // Sätter användarens namn om det inte är null
            if (user.getName() == null || user.getName().isEmpty()) {
                user.setName("Anonym"); // Fallback
            }

            // Spara användare
            user = userRepository.save(user);

            // Genererar JWT
            String token = jwtUtil.generateToken(user.getEmail());

            // Returnera namn, email och token
            return ResponseEntity.ok(new RegisterDTO(user.getName(), user.getEmail(), token));


        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RegisterDTO("Registreringen misslyckades.", "", ""));
        }

    }

    //Logga in
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginHandler(@RequestBody @Valid LoginCredentials body) {
        try {

            // Skapar en Authentication Token som innehåller credentials för authenticating
            // Token används som input till authentication process
            UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
                    body.getEmail(), body.getPassword());

            // Autentisierar Login Credentials
            authManager.authenticate(authInputToken);

            // Hämta användaren från databasen
            User user = userRepository.findByEmail(body.getEmail())
                    .orElseThrow(() -> new RuntimeException("Användare hittades ej."));

            // Om Authentication lyckades
            // Generera JWT
            String token = jwtUtil.generateToken(body.getEmail());

            // Svara med JWT + user info
            Map<String, Object> response = new HashMap<>();
            response.put("jwt-token", token);
            response.put("id", user.getId());
            response.put("name", user.getName());
            response.put("email", user.getEmail());

            return ResponseEntity.ok(response);


        } catch (AuthenticationException authExc) {

            //401-meddelande ifall autentisering misslyckas
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Fel användarnamn/lösenord");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        
         } catch (Exception e) {

            //500-meddelande ifall generellt fel

            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("Message", "Serverfel.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
         }}

          

}
