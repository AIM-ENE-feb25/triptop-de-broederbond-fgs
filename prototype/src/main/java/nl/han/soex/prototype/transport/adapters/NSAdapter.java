package nl.han.soex.prototype.transport.adapters;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import nl.han.soex.prototype.transport.Trip;
import nl.han.soex.prototype.transport.TripRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

public class NSAdapter implements ITransportAdapter {

    @Value("${ns.api.key}")
    private String apiKey;

    private static final String NS_API_URL = "https://gateway.apiportal.ns.nl/reisinformatie-api/api/v3/trips";

    @Override
    public List<Trip> getAvailableTrips(TripRequest tripRequest) throws UnirestException {

        String fromStation = tripRequest.getDeparture();
        String toStation = tripRequest.getDestination();

        HttpResponse<JsonNode> response = Unirest.get(NS_API_URL)
                .queryString("fromStation", fromStation)
                .queryString("toStation", toStation)
                .header("Ocp-Apim-Subscription-Key", apiKey)
                .header("Accept", "application/json")
                .asJson();

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed to retrieve trips " + response.getStatusText());
        }

        return parseTrips(response.getBody());
    }

    private List<Trip> parseTrips(JsonNode responseBody) {
        List<Trip> trips = new ArrayList<>();
        JSONObject jsonResponse = responseBody.getObject();

        JSONArray tripsArray = jsonResponse.optJSONArray("trips");
        if (tripsArray == null) return trips;

        for (int i = 0; i < tripsArray.length(); i++) {
            JSONObject tripObj = tripsArray.getJSONObject(i);

            long tripId = i + 1;
            JSONObject firstLeg = tripObj.getJSONArray("legs").getJSONObject(0);
            String departure = tripObj.getJSONArray("legs").getJSONObject(0).getJSONObject("origin").getString("name");
            String destination = tripObj.getJSONArray("legs").getJSONObject(0).getJSONObject("destination").getString("name");
            String dateTime = tripObj.getString("plannedDepartureDateTime");
            String plannedTrack = firstLeg.getJSONObject("origin").optString("plannedTrack", "N/A");
            String actualTrack = firstLeg.getJSONObject("origin").optString("actualTrack", "N/A");

            trips.add(new Trip(tripId, departure, destination, dateTime, plannedTrack, actualTrack));
        }

        return trips;
    }

}
