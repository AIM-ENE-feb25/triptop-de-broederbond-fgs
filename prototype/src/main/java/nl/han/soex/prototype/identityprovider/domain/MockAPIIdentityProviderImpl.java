package nl.han.soex.prototype.identityprovider.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import nl.han.soex.prototype.identityprovider.security.TokenProvider;
import nl.han.soex.prototype.identityprovider.dto.MockApiResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MockAPIIdentityProviderImpl implements IdentityProvider {

    private final String baseUrl;

    private final TokenProvider tokenProvider;

    public MockAPIIdentityProviderImpl(@Value("${identity-provider.mockapi.baseurl}") String baseUrl, TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
        this.baseUrl = baseUrl;
    }

    @Override
    public String authenticate(String token, List<String> options) {
        ObjectMapper objectMapper = new ObjectMapper();
        MockApiResponseDTO dto;

        try {
            HttpResponse<JsonNode> response = Unirest.post(baseUrl)
                    .queryString("token", token)
                    .body("{username: '%s', application: '%s'}".formatted(options.get(0), options.get(1)))
                    .asJson();

            dto = objectMapper.readValue(response.getBody().toString(), MockApiResponseDTO.class);
        } catch (UnirestException | JsonProcessingException ex) {
            return "";
        }

        boolean isValid = dto.access().equals("allowed") && dto.role().equals("klant");
        return isValid ? tokenProvider.generateToken(options.get(0)) : "";
    }

    @Override
    public boolean isValidToken(String token) {
        return tokenProvider.isValid(token);
    }

    @Override
    public String getProviderName() {
        return "mockapi";
    }
}
