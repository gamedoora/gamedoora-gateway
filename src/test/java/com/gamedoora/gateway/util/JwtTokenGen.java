package com.gamedoora.gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtTokenGen {
    public static String generateToken() {
        String secretKey = "Your_Token"; // Replace with your actual secret key
        // Define a key for signing the token (replace with your secret)
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

        // Define token claims
        Claims claims = Jwts.claims()
                .setSubject("testing")
                .setIssuer("gamedoora")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)); // Token expires in 1 hour

        // Build and sign the token
        return Jwts.builder()
                .setClaims(claims)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
