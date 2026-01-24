package com.example.Ecommerce.jwtUtility;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET = "hduhdidhisuidhuhduuuhdgdggdtdtdttdtdttddvdvvdvdcdteersccrescscrcs";

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public String extractEmail(String token) {
        return getClaims(token).getSubject(); // the subject = email
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = getClaims(token);
            String email = claims.getSubject();
            Date expiration = claims.getExpiration();

            return (email != null && expiration.after(new Date()));
        }
        catch (Exception e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody(); // gives payload (subject, issuedAt, expiration)
    }
}
