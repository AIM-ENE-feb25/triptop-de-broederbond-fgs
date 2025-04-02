package com.github.axaca.prototype.service;

import com.github.axaca.prototype.provider.IdentityProvider;
import com.github.axaca.prototype.provider.IdentityProviderFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final IdentityProviderFactory identityProviderFactory;

    public AuthService(IdentityProviderFactory identityProviderFactory) {
        this.identityProviderFactory = identityProviderFactory;
    }

    public boolean validateToken(String token, String type, List<String> options) {
        IdentityProvider identityProvider = identityProviderFactory.getIdentityProvider(type);
        return identityProvider.validateToken(token, options);
    }

}
