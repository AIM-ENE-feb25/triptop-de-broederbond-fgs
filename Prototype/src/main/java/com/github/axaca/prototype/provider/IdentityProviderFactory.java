package com.github.axaca.prototype.provider;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IdentityProviderFactory {

    private static Map<String, IdentityProvider> identityProviders = Map.of();

    public IdentityProviderFactory(List<IdentityProvider> services) {
        identityProviders = services.stream()
                .collect(Collectors.toMap(IdentityProvider::getName, identityProvider -> identityProvider));
    }

    public static IdentityProvider getIdentityProvider(String type) {
        return Optional.ofNullable(identityProviders.get(type))
                .orElseThrow(() -> new IllegalArgumentException("Unknown service type: " + type));
    }

    public static Map<String, IdentityProvider> getIdentityProviders() {
        return identityProviders;
    }
}
