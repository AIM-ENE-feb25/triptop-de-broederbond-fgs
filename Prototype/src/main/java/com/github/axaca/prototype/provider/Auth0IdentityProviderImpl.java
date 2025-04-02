package com.github.axaca.prototype.provider;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Auth0IdentityProviderImpl implements IdentityProvider {

    private final String issuer = "https://dev-1yukta3ze7ly4fnr.eu.auth0.com/";

    @Override
    public boolean validateToken(String token, List<String> options) {
        JwtDecoder jwtDecoder = JwtDecoders.fromIssuerLocation(issuer);
        try {
            Jwt jwt = jwtDecoder.decode(token);
            System.out.println(123456);
            return jwt != null && jwt.getClaim("exp") != null; // Basic validation
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public String getName() {
        return "auth0";
    }
}
