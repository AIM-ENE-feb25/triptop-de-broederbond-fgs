package nl.han.soex.prototype.transport;

import com.mashape.unirest.http.exceptions.UnirestException;
import nl.han.soex.prototype.transport.adapters.ITransportAdapter;
import nl.han.soex.prototype.transport.factory.AdapterFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportService {

    private final AdapterFactory adapterFactory;

    public TransportService(AdapterFactory adapterFactory) {
        this.adapterFactory = adapterFactory;
    }

    public List<Trip> getAvailableTrips(TripRequest tripRequest) throws UnirestException {
        TransportType transportType = extractTransportType(tripRequest);
        ITransportAdapter adapter = adapterFactory.getAdapter(transportType);
        return adapter.getAvailableTrips(tripRequest);
    }

    private TransportType extractTransportType(TripRequest request) {
        return request.getTransportType();
    }

}
