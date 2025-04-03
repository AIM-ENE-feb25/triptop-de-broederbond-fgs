package nl.han.soex.prototype.identityprovider.domain;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class IdentityProviderFactory {

    @Getter
    private static Map<String, IdentityProvider> identityProviders = Map.of();

    public IdentityProviderFactory(List<IdentityProvider> services) {
        identityProviders = services.stream()
                .collect(Collectors.toMap(IdentityProvider::getProviderName, identityProvider -> identityProvider));
    }

    public static IdentityProvider getIdentityProvider(String type) {
        return Optional.ofNullable(identityProviders.get(type))
                .orElseThrow(() -> new IllegalArgumentException("Unknown service type: " + type));
    }

}
