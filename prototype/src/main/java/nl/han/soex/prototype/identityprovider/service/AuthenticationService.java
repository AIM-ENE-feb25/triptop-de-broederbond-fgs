package nl.han.soex.prototype.identityprovider.service;

import nl.han.soex.prototype.identityprovider.domain.IdentityProvider;
import nl.han.soex.prototype.identityprovider.domain.IdentityProviderFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {

    public String authenticate(String token, String name, List<String> options) {
        IdentityProvider identityProvider = IdentityProviderFactory.getIdentityProvider(name);
        return identityProvider.authenticate(token, options);
    }

}
