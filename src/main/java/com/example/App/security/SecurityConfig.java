package com.example.App.security;

import com.example.App.service.MyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity // Sätter igång security för applikationen
public class SecurityConfig {

        // Injecting Dependencies
        @Autowired
        private JWTFilter filter;
        @Autowired
        private MyUserDetailsService uds;

        // Metod som konfiguerar security kring app

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                return http
                                .csrf(csrf -> csrf.disable()) // Stänger av CSRF-skydd
                                .cors(cors -> {})             // Aktiverar CORS

                                //Behörighetsregler
                                .authorizeHttpRequests(auth -> auth
                                                                  
                                  .requestMatchers("/api/auth/**").permitAll()                 // Tillåter ej autentiserade anrop
                                  .anyRequest().authenticated()                                // Alla andra requests kräver autentisering
                                  )
                                 
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless sessionhantering
                                )

                                .exceptionHandling(exceptions -> exceptions
                                                .authenticationEntryPoint((request, response, authException) -> 
                                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"))
                                )

                                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class) // Lägg till JWT-filter
                                .userDetailsService(uds)                                                          // Använd UserDetailsService
                                .httpBasic(httpBasic -> {})                                                       // Aktivera HTTP Basic Authentication
                                .build();
        }

        // Krypterar lösenord
        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        // Kontrollerar att användarens inloggning är korrekt enligt authentication manager
        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
                        throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }

}