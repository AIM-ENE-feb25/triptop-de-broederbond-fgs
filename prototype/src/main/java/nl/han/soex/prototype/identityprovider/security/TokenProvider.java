package nl.han.soex.prototype.identityprovider.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.sql.Date;
import java.time.LocalDate;

@Component
public class TokenProvider {
    @Value("${app.jwt-secret}")
    private String secret;

    @Value("${app.jwt-expiration-days}")
    private long expirationDate;

    public String generateToken(String username) {
        Date issuedAt = new Date(System.currentTimeMillis());
        LocalDate expireDate = issuedAt.toLocalDate().plusDays(expirationDate);
        return generateToken(username, issuedAt, expireDate);
    }

    public String generateToken(String username, Date issuedAt, LocalDate expireDate) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(issuedAt)
                .expiration(Date.valueOf(expireDate))
                .signWith(getSecretKey())
                .compact();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean isValid(String token) throws RuntimeException {
        try {
            Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parse(token);
            return true;
        } catch (ExpiredJwtException | MalformedJwtException | SecurityException ex) {
            return false;
        }
    }
}
