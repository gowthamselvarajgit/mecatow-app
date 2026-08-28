package com.mecatow.user_service.security;

import com.mecatow.user_service.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private final String SECRET_KEY =
            "mecatow-secret-key-for-jwt-authentication-2026";

    private static final long JWT_EXPIRATION = 24 * 60 * 60 * 1000;

    private SecretKey getSignInKey(){
        return Keys.hmacShaKeyFor(
                SECRET_KEY.getBytes()
        );
    }

    public String generateToken(User user){
        Map<String, Object> claims = new HashMap<>();

        claims.put("userId", user.getUserId());
        claims.put("role", user.getRole().name());

        return Jwts.builder()
                .claims(claims)
                .subject(user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(
                        System.currentTimeMillis() + JWT_EXPIRATION
                ))
                .signWith(getSignInKey())
                .compact();
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token){
        return extractAllClaims(token)
                .getSubject();
    }

    public Date extractExpiration(String token){
        return extractAllClaims(token)
                .getExpiration();
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token)
                .before(new Date());
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        String email = extractUsername(token);

        return email.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

}