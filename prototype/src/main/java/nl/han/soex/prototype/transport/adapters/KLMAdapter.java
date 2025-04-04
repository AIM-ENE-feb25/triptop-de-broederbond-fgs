package nl.han.soex.prototype.transport.adapters;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import nl.han.soex.prototype.transport.FlightTrip;
import nl.han.soex.prototype.transport.Trip;
import nl.han.soex.prototype.transport.TripRequest;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class KLMAdapter implements ITransportAdapter {

    private final String apiKey = "jhdbdb8jrsv947uemt35gkd9";

    private static final String KLM_API_URL = "https://api.airfranceklm.com/opendata/offers/v3/available-offers";

    @Override
    public List<Trip> getAvailableTrips(TripRequest tripRequest) throws IOException, InterruptedException {

        String fromLocation = tripRequest.getDeparture();
        String toLocation = tripRequest.getDestination();
        String departureDate = tripRequest.getDate();

        JSONObject bodyToSend = buildRequestBody(fromLocation, toLocation, departureDate);


            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(KLM_API_URL))
                    .header("Content-Type", "application/json")
                    .header("API-Key", apiKey)
                    .header("AFKL-TRAVEL-Host", "KL")
                    .POST(HttpRequest.BodyPublishers.ofString(bodyToSend.toString()))
                    .build();

            java.net.http.HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject json = new JSONObject(response.body());

            if (response.statusCode() != 200) {
                throw new RuntimeException("Failed to retrieve trips from KLM API: " + response.body());
            }


        return parseTrips(json, fromLocation, toLocation, departureDate);
    }

    private JSONObject buildRequestBody(String fromLocation, String toLocation, String departureDate) {
        JSONObject requestBody = new JSONObject();

        requestBody.put("commercialCabins", new JSONArray().put("ALL"));
        requestBody.put("bookingFlow", "LEISURE");

        JSONArray passengers = new JSONArray();
        JSONObject passenger = new JSONObject();
        passenger.put("id", 1);
        passenger.put("type", "ADT");
        passengers.put(passenger);
        requestBody.put("passengers", passengers);

        JSONArray requestedConnections = new JSONArray();
        JSONObject connection = new JSONObject();
        connection.put("departureDate", departureDate);
        connection.put("origin", new JSONObject().put("code", fromLocation).put("type", "STOPOVER"));
        connection.put("destination", new JSONObject().put("code", toLocation).put("type", "STOPOVER"));
        requestedConnections.put(connection);
        requestBody.put("requestedConnections", requestedConnections);

        return requestBody;
    }


    public List<Trip> parseTrips(JSONObject responseBody, String fromLocation, String toLocation, String departureDate) {
        List<Trip> trips = new ArrayList<>();
        JSONObject jsonResponse = responseBody;

        JSONArray recommendations = jsonResponse.optJSONArray("recommendations");
        if (recommendations == null) return trips;

        for (int i = 0; i < recommendations.length(); i++) {
            JSONObject recommendation = recommendations.getJSONObject(i);

            JSONArray flightProducts = recommendation.optJSONArray("flightProducts");
            if (flightProducts != null) {
                for (int j = 0; j < flightProducts.length(); j++) {
                    JSONObject flightProduct = flightProducts.getJSONObject(j);

                    double displayPrice = flightProduct.optJSONObject("price") != null ?
                            flightProduct.getJSONObject("price").optDouble("displayPrice", 0.0) : 0.0;

                    JSONArray connections = flightProduct.optJSONArray("connections");
                    if (connections != null) {
                        for (int k = 0; k < connections.length(); k++) {
                            JSONObject connection = connections.getJSONObject(k);

                            FlightTrip flightTrip = new FlightTrip(
                                    fromLocation,
                                    toLocation,
                                    departureDate,
                                    displayPrice
                            );

                            trips.add(flightTrip);
                        }
                    }
                }
            }
        }

        return trips;
    }

}
