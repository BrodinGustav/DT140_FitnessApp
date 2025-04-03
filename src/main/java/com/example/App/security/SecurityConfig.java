package com.example.App.security;

import com.example.App.repository.UserRepository;
import com.example.App.service.MyUserDetailsService;

import java.beans.Customizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.security.config.Customizer.withDefaults;

import jakarta.servlet.http.HttpServletResponse;

@Configuration // Marks this as a configuration file
@EnableWebSecurity // Enables security for this application
public class SecurityConfig {

    // Injecting Dependencies
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTFilter filter;
    @Autowired
    private MyUserDetailsService uds;

   
    // Method to configure your app security

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Stänger av CSRF-skydd
                .cors(cors -> {}) // Aktiverar CORS

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()  // Tillåter ej autentiserade anrop
                        .requestMatchers("/api/user/**").hasRole("USER") // Kräver "USER"-roll
                        .anyRequest().authenticated() // Alla andra requests kräver autentisering
                )

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless sessionhantering
                )

                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint((request, response, authException) -> 
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"))
                )

                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class) // Lägg till JWT-filter
                .userDetailsService(uds) // Använd UserDetailsService
                .httpBasic(httpBasic -> {}) // Aktivera HTTP Basic Authentication
                .build();
    }

    // Creating a bean for the password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Exposing the bean of the authentication manager which will be used to run the
    // authentication process
   @Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
}

}