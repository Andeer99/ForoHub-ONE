package com.alurachallenge.Forohub.infra.security;

import com.alurachallenge.Forohub.domain.usuarios.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TokenSevice {
    public String generateToken(Usuario usuario) {
        try {
            RSAPrivateKey rsaPrivateKey = null;
            RSAPublicKey rsaPublicKey = null;
            Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
            return JWT.create()
                    .withIssuer("ForoHub")
                    .withSubject(usuario.getLogin())
                    .withArrayClaim("id", usuario.getId)
                    .withExpiresAt(generarFechDeExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException(exception);
        }
    }
    private Instant generarFechDeExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-5:00"));
    }
}
