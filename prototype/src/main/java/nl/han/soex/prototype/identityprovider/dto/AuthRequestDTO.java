package nl.han.soex.prototype.identityprovider.dto;

import java.util.List;

public record AuthRequestDTO(String name, String token, List<String> options) {
}
