package com.github.axaca.prototype.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Auth0IdentityProviderImpl implements IdentityProvider {

    private final String issuer = "https://dev-1yukta3ze7ly4fnr.eu.auth0.com/";
    private final String SECRET_KEY = "MIIDHTCCAgWgAwIBAgIJD2pWdKKp2RAnMA0GCSqGSIb3DQEBCwUAMCwxKjAoBgNVBAMTIWRldi0xeXVrdGEzemU3bHk0Zm5yLmV1LmF1dGgwLmNvbTAeFw0yNTA0MDIxMjI0MzdaFw0zODEyMTAxMjI0MzdaMCwxKjAoBgNVBAMTIWRldi0xeXVrdGEzemU3bHk0Zm5yLmV1LmF1dGgwLmNvbTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAL8H4HQVL+TqR/9vB78syvWolhg5WIgOnzJYypS5AeSOf77wWUC0mP33F5RgwzLu32Vump+sHkf4fVJG71rcI867VWc0yQNxdslyK0AihyUP1Fv8NgvjfJY+I87vFLB8ArBXLMhBMJ50oV8ZcQVhsfVE78rBK1F0eC4vY0UnZgEC2zGygeEfezSHfJPiOHaj/8IKZgwVgXF/Qes2IOJ+UbY+03zf+hKpwEVRnNxZmuPK+6/jn495bX1HfOD24JMKsjjZMc9PGDyeKcRP1TwbKTVAvNmeawFaLAEuqea2WhZb8ibqPnZwTnwXKbnJ+Flp1FeWr4GRTtFYGfKy8IwAmAsCAwEAAaNCMEAwDwYDVR0TAQH/BAUwAwEB/zAdBgNVHQ4EFgQUEoHsDSUa8ZzyRO313ajWmWeLa2swDgYDVR0PAQH/BAQDAgKEMA0GCSqGSIb3DQEBCwUAA4IBAQC6BdIZ0zaZTdtcnEKmRAx4uZ0o1Ywt6biktOnQqZvkAEBdjHnl9cQruQL6DC6fbAcE2vGhZ7lf5Iw0jIWeIXUdqEAMWeDyzxERJKzzOPo8X8FwgUApVfBaktghrsfaCLHD3QGa2MqKOM9Z3x5DzrAsvGF0KWBEpdxJAD9CaY3Pj+hUKdvC1LCNKZBClorWrJ+hr2wHFmzL1n6H+XhAVloZnVTjawiYhlPpt6J+r/GPKa1x8qTcPBxBz8lj6t5ghFkgBBAS9h8a7+hNVtfkXtF8IONzjpkGvJBYp/5pqRdASonR6+dsCdvbugkuLvBaqUUmwgJpMep4mg34RtMAuuMY";

    @Override
    public boolean validateToken(String token, List<String> options) {
        System.out.println("Validating auth0 token: " + token);

        try {
            var a=Jwts.parser()
                            .build()
                                    .parseSignedClaims(token);
            System.out.println(a);


//            // If token is valid, process the claims
//            System.out.println("Token is valid.");
//            System.out.println("Issuer: " + claims.getIssuer());
//            System.out.println("Subject: " + claims.getSubject());
//            System.out.println("Expiration: " + claims.getExpiration());



            return true;
        } catch (ExpiredJwtException | MalformedJwtException | SecurityException ex) {
            return false;
        }
    }

    @Override
    public String getName() {
        return "auth0";
    }
}
