package persoonlijkeUitwerkingen.WireMockAPI;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WireMockAPI {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://triptop-identity.wiremockapi.cloud/login"))
                .POST(HttpRequest.BodyPublishers.ofString("{" +
                        "\"username\":\"edevries\"," +
                        "\"password\":\"3g2Rw9sT1x\"}")
                )
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        String tokenValue = response.body().split("\"value\" : \"")[1].split("\"")[0];
        String expirationTime = response.body().split("\"expirationTime\" : \"")[1].split("\"")[0];
        Token token = new Token(tokenValue, expirationTime);

        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(URI.create("https://triptop-identity.wiremockapi.cloud/checkAppAccess?token=" + token.value))
                .POST(HttpRequest.BodyPublishers.ofString("{" +
                        "\"username\":\"edevries\"," +
                        "\"application\":\"triptop\"" +
                        "}")
                )
                .build();
        HttpResponse<String> response2 = HttpClient.newHttpClient().send(request2, HttpResponse.BodyHandlers.ofString());
        System.out.println(response2.body());
    }
}
