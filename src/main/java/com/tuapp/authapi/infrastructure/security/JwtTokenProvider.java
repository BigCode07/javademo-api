package com.tuapp.authapi.infrastructure.security;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.tuapp.authapi.application.port.out.TokenProvider;
import com.tuapp.authapi.domain.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component 
public class JwtTokenProvider implements  TokenProvider{
    private final SecretKey key;
    private final Duration duration;

     public JwtTokenProvider(
            @Value("${app.security.jwt.secret}") String secret,
            @Value("${app.security.jwt.expiration-minutes}") long expirationMinutes) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.duration = Duration.ofMinutes(expirationMinutes);
    }

      @Override
    public String generate(User user) {
        Date now = new Date();
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("email", user.getEmail())
                .issuedAt(now)
                .expiration(new Date(now.getTime() + duration.toMillis()))
                .signWith(key)
                .compact();
    }
}
