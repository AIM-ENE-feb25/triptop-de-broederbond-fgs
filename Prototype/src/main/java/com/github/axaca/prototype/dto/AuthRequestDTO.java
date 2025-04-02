package com.github.axaca.prototype.dto;

import java.util.List;

public record AuthRequestDTO(String name, String token, List<String> options) {
}
