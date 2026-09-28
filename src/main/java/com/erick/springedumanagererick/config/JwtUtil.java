package com.erick.springedumanagererick.config;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private final Key clave = Keys.hmacShaKeyFor(
        "una-clave-secreta-bien-larga-para-firmar-los-tokens-jwt-1234567890".getBytes()
    );

    private final long duracionMs = 1000 * 60 * 60; // 1 hora

    public String generarToken(String username, String rol) {
        Date ahora = new Date();
        Date expiracion = new Date(ahora.getTime() + duracionMs);

        return Jwts.builder()
                .subject(username)
                .claim("rol", rol)
                .issuedAt(ahora)
                .expiration(expiracion)
                .signWith(clave)
                .compact();
    }

    public String extraerUsername(String token) {
        return parsearClaims(token).getSubject();
    }

    public String extraerRol(String token) {
        return parsearClaims(token).get("rol", String.class);
    }

    public boolean esTokenValido(String token) {
        try {
            Claims claims = parsearClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private Claims parsearClaims(String token) {
        return Jwts.parser()
                .verifyWith((javax.crypto.SecretKey) clave)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}