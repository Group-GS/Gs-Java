package br.com.gs.projeto_agro.security;

import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

@Component
public class JWTUtil {

    
    private final SecretKey CHAVE =
            Jwts.SIG.HS256.key().build();

    public String gerarToken(String email, Integer duracaoMinutos) {

        Date agora = new Date();

        JwtBuilder builder = Jwts.builder()
                .subject(email)
                .issuedAt(agora)
                .expiration(new Date(agora.getTime() + (1000L * 60 * duracaoMinutos)))
                .signWith(CHAVE);

        return builder.compact();
    }

    
    public String extrairEmail(String token) {

        JwtParser parser = Jwts.parser()
                .verifyWith(CHAVE)
                .build();

        return parser.parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validarToken(String token) {
        try {
            JwtParser parser = Jwts.parser()
                    .verifyWith(CHAVE)
                    .build();

            parser.parseSignedClaims(token);
            return true;

        } catch (Exception e) {
            return false;
        }
    }
}