package com.example.App.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.App.service.MyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component //Komponent. Spring Boot skapar och hanterar JWTFilter Bean
// JWTFilter bean kan nu injectas i andra delar av koden
public class JWTFilter extends OncePerRequestFilter {

    // Injecting Dependencies
    @Autowired private MyUserDetailsService userDetailsService;
    @Autowired private JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        
        //Extraherar "Authorization" header
        String authHeader = request.getHeader("Authorization");

        //Kontroll om header innehåller en Bearer token
        if(authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")){
            
            //Extraherar JWT
            String jwt = authHeader.substring(7);
            if(jwt == null || jwt.isBlank()){
                
                //Invalid JWT
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token i Bearer Header");
            }else {
                try{
                    //Verifierar token och extraherar email
                    String email = jwtUtil.validateTokenAndRetrieveSubject(jwt);

                    //Fetch User Details
                    UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                    //Skapar Authentication Token
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(email, userDetails.getPassword(), userDetails.getAuthorities());

                    //Sätter autentisiering till Security Context via skapad token
                    if(SecurityContextHolder.getContext().getAuthentication() == null){
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }catch(JWTVerificationException exc){
                    
                    //Misslyckades att verifiera JWT
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token");
                }
            }
        }

        //Fortsätter med fitler chain
        filterChain.doFilter(request, response);
    }
}