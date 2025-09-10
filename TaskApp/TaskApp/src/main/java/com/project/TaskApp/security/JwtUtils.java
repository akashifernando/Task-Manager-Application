package com.project.TaskApp.security;

//handle token creatiom parsing valid
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;
@Component

public class JwtUtils {

    private final String SECRET_KEY = "secret";
    @Value("${secreteJwtString}")
    private String secreteJwtString;
    private SecretKey key;

    @PostConstruct
    private void init(){
        byte[] keyByte = secreteJwtString.getBytes(StandardCharsets.UTF_8);
        this.key = new SecretKeySpec(keyByte, "HmacSHA256");
    }

    public String  generateToken(String username){
        return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                    .signWith(key)
                    .compact();
    }
    public String extractUsername(String token) {
            return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
        }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }


//    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction){
//        return claimsTFunction.apply(Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload());
//    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getExpiration();
        return expiration.before(new Date());
    }

//









}
