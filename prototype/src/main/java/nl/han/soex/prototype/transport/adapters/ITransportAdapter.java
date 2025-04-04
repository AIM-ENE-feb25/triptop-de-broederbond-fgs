package nl.han.soex.prototype.transport.adapters;

import com.mashape.unirest.http.exceptions.UnirestException;
import nl.han.soex.prototype.transport.Trip;
import nl.han.soex.prototype.transport.TripRequest;

import java.io.IOException;
import java.util.List;

public interface ITransportAdapter {

    List<Trip> getAvailableTrips(TripRequest tripRequest) throws UnirestException, IOException, InterruptedException;

}
