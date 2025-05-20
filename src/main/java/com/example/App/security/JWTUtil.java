package com.example.App.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    //Injicerar jwt_secret från application.properties
    @Value("${jwt_secret}")
    private String secret;

    //Metod för att signera och skapa JWT genom injicerad jwt_secret
    public String generateToken(String email) throws IllegalArgumentException, JWTCreationException {
        return JWT.create()
                .withSubject("User Details")
                .withClaim("email", email)
                .withIssuedAt(new Date())
                .withIssuer("YOUR APPLICATION/PROJECT/COMPANY NAME")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) //Token håller 1 timma
                .sign(Algorithm.HMAC256(secret));
    }

    //Metod som verifierar JWT, dekodar och extraherar email lagrad i tokens payload
    public String validateTokenAndRetrieveSubject(String token)throws JWTVerificationException {
        try {

        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User Details")
                .withIssuer("YOUR APPLICATION/PROJECT/COMPANY NAME")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("email").asString();

    } catch (JWTVerificationException e) {
        throw new JWTVerificationException("Verifikation av JWT misslyckades: " + e.getMessage());
    }
    
    }

}