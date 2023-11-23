package br.com.reloadCare.api.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.reloadCare.api.models.Credencial;
import br.com.reloadCare.api.models.Token;
import br.com.reloadCare.api.models.User;
import br.com.reloadCare.api.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.secret}")
    String secret;

    public Token generateToken(@Valid Credencial credencial, Long userId) {
        Algorithm alg = Algorithm.HMAC256(secret);
        String token = JWT.create()
                .withSubject(credencial.email())
                .withIssuer("ReloadCare")
                .withExpiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
                .sign(alg);
        return new Token(token, "JWT", "Bearer", userId);
    }

    public User getValidateUser(String token) {
        Algorithm alg = Algorithm.HMAC256(secret);
        var email = JWT.require(alg)
                .withIssuer("ReloadCare")
                .build()
                .verify(token)
                .getSubject();

        return userRepository.findByEmail(email).orElseThrow(() -> new JWTVerificationException("Usuario invalido"));
    }
}
