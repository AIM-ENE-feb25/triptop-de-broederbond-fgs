package nl.han.soex.prototype.transport.adapters;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import nl.han.soex.prototype.transport.TrainTrip;
import nl.han.soex.prototype.transport.Trip;
import nl.han.soex.prototype.transport.TripRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class NSAdapter implements ITransportAdapter {

//    @Value("${ns.api.key}")
    private final String apiKey = "e7181d47c68640d7ba5ac1d53b776ac5";

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
            System.out.println(response.getBody());
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
            String departure = firstLeg.getJSONObject("origin").getString("name");
            String destination = firstLeg.getJSONObject("destination").getString("name");
            String dateTime = firstLeg.getJSONObject("origin").getString("plannedDateTime");


            String plannedTrack = firstLeg.getJSONObject("origin").optString("plannedTrack", "N/A");
            String actualTrack = firstLeg.getJSONObject("origin").optString("actualTrack", "N/A");

            TrainTrip trainTrip = new TrainTrip(
                tripId,
                departure,
                destination,
                dateTime,
                plannedTrack,
                actualTrack
            );


            trips.add(trainTrip);

        }

        return trips;
    }

}
