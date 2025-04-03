package nl.han.soex.prototype.identityprovider.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Auth0IdentityProviderImpl implements IdentityProvider {

    private final JwtDecoder jwtDecoder;
    private final String issuer;

    public Auth0IdentityProviderImpl(@Value("${identity-provider.auth0.issuer}") String issuer) {
        jwtDecoder = JwtDecoders.fromIssuerLocation(issuer);;
        this.issuer = issuer;
    }

    @Override
    public String authenticate(String token, List<String> options) {
        if (!isValidToken(token)) {
            token = "";
        }
        return token;
    }

    @Override
    public boolean isValidToken(String token) {
        try {
            Jwt jwt = jwtDecoder.decode(token);
            return jwt != null && jwt.getClaim("iss").equals(issuer) && jwt.getClaim("exp") != null;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getProviderName() {
        return "auth0";
    }
}
