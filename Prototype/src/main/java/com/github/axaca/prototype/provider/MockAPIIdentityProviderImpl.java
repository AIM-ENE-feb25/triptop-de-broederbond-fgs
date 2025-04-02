package com.github.axaca.prototype.provider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.axaca.prototype.dto.MockApiResponseDTO;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MockAPIIdentityProviderImpl implements IdentityProvider {

    private String baseUrl = "http://identity-wiremock.minordevops.nl:8080/checkAppAccess";

    @Override
    public boolean validateToken(String token, List<String> options) {
        ObjectMapper objectMapper = new ObjectMapper();
        MockApiResponseDTO dto;

        try {
            HttpResponse<JsonNode> response = Unirest.post(baseUrl)
                    .queryString("token", token)
                    .body("{username: '%s', application: '%s'}".formatted(options.get(0), options.get(1)))
                    .asJson();

            dto = objectMapper.readValue(response.getBody().toString(), MockApiResponseDTO.class);
        } catch (UnirestException | JsonProcessingException ex) {
            return false;
        }

        System.out.println(dto);
        return dto.access().equals("allowed") && dto.role().equals("klant");
    }

    @Override
    public String getName() {
        return "mockapi";
    }
}
