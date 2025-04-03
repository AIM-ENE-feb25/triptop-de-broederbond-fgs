package nl.han.soex.prototype.transport;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/transport")
public class TransportController {

    private final TransportService transportService;

    @Autowired
    public TransportController(TransportService transportService) {
        this.transportService = transportService;
    }

    // POST omdat GET geen body toe staat
    // en body gebruikt wordt voor factory
    @PostMapping("/trips")
    public ResponseEntity<String> getAvailableTrips(@RequestBody TripRequest tripRequest) throws UnirestException {
        List<Trip> trips = transportService.getAvailableTrips(tripRequest);
        return ResponseEntity.ok(trips.toString());
    }

}
