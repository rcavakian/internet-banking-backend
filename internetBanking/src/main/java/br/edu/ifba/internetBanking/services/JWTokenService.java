package br.edu.ifba.internetBanking.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.edu.ifba.internetBanking.entities.User;

@Service
public class JWTokenService {
    @Value("${jwt.secret}")
    private String secret;

    public String genereteToken(User user) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
            .withIssuer("InternetBanking API")
            .withSubject(user.getCpf())
            .withExpiresAt(dateExpiration())
            .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("error generating jwt token", exception);
        }
    }

    private Instant dateExpiration() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String tokenJWT) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
            .withIssuer("InternetBanking API")
            .build()
            .verify(tokenJWT)
            .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Invalid or expired JWT token");
        }
    }
}
