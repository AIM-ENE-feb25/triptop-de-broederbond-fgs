package nl.han.soex.prototype.identityprovider.domain;

import java.util.List;

public interface IdentityProvider {
    String authenticate(String token, List<String> options);
    boolean isValidToken(String token);
    String getProviderName();
}
