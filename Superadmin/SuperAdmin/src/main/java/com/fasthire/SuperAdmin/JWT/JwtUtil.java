package com.fasthire.SuperAdmin.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationTime;

    // Generate SecretKey for JWT signing
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    // Generate JWT Token
    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)  // setSubject() is replaced with subject()
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSigningKey()) // No need for SignatureAlgorithm.HS256
                .compact();
    }

    // Validate Token
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey()) // Correct method in JJWT 0.12.3
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Extract Email from Token
    public String extractEmail(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey()) // Correct method
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }
}
