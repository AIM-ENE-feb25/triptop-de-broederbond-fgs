package com.github.axaca.prototype.controller;

import com.github.axaca.prototype.dto.AuthRequestDTO;
import com.github.axaca.prototype.dto.AuthResponseDTO;
import com.github.axaca.prototype.provider.IdentityProvider;
import com.github.axaca.prototype.provider.IdentityProviderFactory;
import com.github.axaca.prototype.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/validate")
    public ResponseEntity<AuthResponseDTO> validateToken(@RequestBody AuthRequestDTO authRequestDTO) {
        boolean isValidated = authService.validateToken(authRequestDTO.token(), authRequestDTO.name(), authRequestDTO.options());
        return new ResponseEntity<>(new AuthResponseDTO(authRequestDTO.token(), isValidated), HttpStatus.OK);
    }

    @GetMapping("/providers")
    public ResponseEntity<List<String>> providers() {
        return new ResponseEntity<>(IdentityProviderFactory.getIdentityProviders().values().stream()
                .map(IdentityProvider::getName)
                .toList(), HttpStatus.OK);
    }

}
