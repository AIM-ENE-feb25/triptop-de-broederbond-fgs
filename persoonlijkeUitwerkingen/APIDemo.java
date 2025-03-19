package persoonlijkeUitwerkingen;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello, World!");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://aerodatabox.p.rapidapi.com/flights/airports/iata/YYZ?offsetMinutes=-120&durationMinutes=720&withLeg=true&direction=Both&withCancelled=true&withCodeshared=true&withCargo=true&withPrivate=true&withLocation=false"))
                .header("x-rapidapi-key", "40512a5185msh18a05cbe6460fd1p16093bjsnd771c1acd12a")
                .header("x-rapidapi-host", "aerodatabox.p.rapidapi.com")
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}
