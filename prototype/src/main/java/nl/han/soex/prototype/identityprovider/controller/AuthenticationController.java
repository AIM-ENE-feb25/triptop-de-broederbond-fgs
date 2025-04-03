package nl.han.soex.prototype.identityprovider.controller;

import nl.han.soex.prototype.identityprovider.service.AuthenticationService;
import nl.han.soex.prototype.identityprovider.domain.IdentityProvider;
import nl.han.soex.prototype.identityprovider.domain.IdentityProviderFactory;
import nl.han.soex.prototype.identityprovider.dto.AuthRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("providers")
    public ResponseEntity<List<String>> getProviders() {
        return new ResponseEntity<>(IdentityProviderFactory.getIdentityProviders().values().stream().map(IdentityProvider::getProviderName).toList(), HttpStatus.OK);
    }

    @PostMapping("authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthRequestDTO authRequestDTO) {
        String token = authenticationService.authenticate(authRequestDTO.token(), authRequestDTO.name(), authRequestDTO.options());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

}
