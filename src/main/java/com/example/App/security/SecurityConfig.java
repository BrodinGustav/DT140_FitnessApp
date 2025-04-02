package com.example.App.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/users/create").permitAll()  //Tillåter alla att registrera sig
                .requestMatchers("/api/users").permitAll()  //Tillåter alla att hämta alla användare
                .requestMatchers("/api/users/{id}").permitAll()  //Tillåter PUT och DELETE 

                .requestMatchers("/api/activities/create").permitAll()  //Tillåter alla att registrera aktiviteter
                .requestMatchers("/api/activities").permitAll()  //Tillåter alla att hämta alla aktiviteter
                .requestMatchers("/api/activities/{id}").permitAll()  //Tillåter PUT och DELETE 

                .requestMatchers("/api/categories/create").permitAll()  
                .requestMatchers("/api/categories").permitAll()  
                .requestMatchers("/api/categories/{id}").permitAll()  

                .requestMatchers("/api/fitness/addActivityForUser").permitAll()  
                .requestMatchers("/api/userActivities").permitAll()  
                .requestMatchers("/api/fitness/{id}").permitAll()  


                .anyRequest().authenticated()
            )
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}