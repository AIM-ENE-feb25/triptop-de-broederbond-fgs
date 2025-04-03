package nl.han.soex.prototype.transport.adapters;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import nl.han.soex.prototype.transport.FlightTrip;
import nl.han.soex.prototype.transport.Trip;
import nl.han.soex.prototype.transport.TripRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

public class KLMAdapter implements ITransportAdapter {

    @Value("${klm.api.key}")
    private String apiKey;

    private static final String KLM_API_URL = "https://api.klm.com/flight-service";

    @Override
    public List<Trip> getAvailableTrips(TripRequest tripRequest) throws UnirestException {

        String fromLocation = tripRequest.getDeparture();
        String toLocation = tripRequest.getDestination();
        String departureDate = tripRequest.getDate();

        HttpResponse<JsonNode> response = Unirest.post(KLM_API_URL)
                .header("Ocp-Apim-Subscription-Key", apiKey)
                .header("Accept", "application/json")
                .body(buildRequestBody(fromLocation, toLocation, departureDate))
                .asJson();

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed to retrieve trips from KLM API: " + response.getStatusText());
        }

        return parseTrips(response.getBody(), fromLocation, toLocation, departureDate);
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


    public List<Trip> parseTrips(JsonNode responseBody, String fromLocation, String toLocation, String departureDate) {
        List<Trip> trips = new ArrayList<>();
        JSONObject jsonResponse = responseBody.getObject();

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
