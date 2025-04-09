package com.example.App.security;

import com.example.App.service.MyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
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
// @Profile("!nosecurity")
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
                                .cors(cors -> {
                                }) // Aktiverar CORS

                                //För felsökning
                                .authorizeHttpRequests(auth -> auth
                                                .anyRequest().permitAll())
                                /*
                                 * .authorizeHttpRequests(auth -> auth
                                 * .requestMatchers("/api/auth/**").permitAll() // Tillåter ej autentiserade
                                 * anrop
                                 * .requestMatchers("/api/users/**").permitAll() // Tillåter ej autentiserade
                                 * anrop
                                 * .requestMatchers("/api/activities/**").permitAll() // Tillåter ej
                                 * autentiserade anrop
                                 * .requestMatchers("/api/categories/**").permitAll() // Tillåter ej
                                 * autentiserade anrop
                                 * .requestMatchers("/api/useractivities/**").permitAll() // Tillåter ej
                                 * autentiserade anrop
                                 * .requestMatchers("/api/user/**").hasRole("USER") // Kräver "USER"-roll
                                 * .anyRequest().authenticated() // Alla andra requests kräver autentisering
                                 * )
                                 */
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless
                                                                                                        // sessionhantering
                                )

                                .exceptionHandling(exceptions -> exceptions
                                                .authenticationEntryPoint((request, response, authException) -> response
                                                                .sendError(HttpServletResponse.SC_UNAUTHORIZED,
                                                                                "Unauthorized")))

                                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class) // Lägg till
                                                                                                     // JWT-filter
                                .userDetailsService(uds) // Använd UserDetailsService
                                .httpBasic(httpBasic -> {
                                }) // Aktivera HTTP Basic Authentication
                                .build();
        }

        // Skapar bean för password encoder
        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        // Exponerar bean av authentication manager vilken kör
        // authentication processen
        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
                        throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }

}