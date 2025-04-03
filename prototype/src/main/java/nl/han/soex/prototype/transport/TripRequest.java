package nl.han.soex.prototype.transport;

public class TripRequest {

    private Long tripId;
    private String departure;
    private String destination;
    private String date;
    private TransportType transportType;

    public TripRequest(Long tripId, String departure, String destination, String date, TransportType transportType) {
        this.tripId = tripId;
        this.departure = departure;
        this.destination = destination;
        this.date = date;
        this.transportType = transportType;
    }

    public Long getTripId() { return tripId; }
    public void setTripId(Long tripId) { this.tripId = tripId; }

    public String getDeparture() { return departure; }
    public void setDeparture(String departure) { this.departure = departure; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public TransportType getTransportType() {
        return transportType;
    }
}
