package nl.han.soex.prototype.transport.adapters;

import nl.han.soex.prototype.transport.Trip;
import nl.han.soex.prototype.transport.TripRequest;

import java.util.List;

public class KLMAdapter implements ITransportAdapter {
    @Override
    public List<Trip> getAvailableTrips(TripRequest tripRequest) {
        return null;
    }
}
