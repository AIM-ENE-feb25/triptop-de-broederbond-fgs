package com.github.axaca.prototype.provider;

import java.util.List;

public interface IdentityProvider {
    boolean validateToken(String token, List<String> options);
    String getName();
}
