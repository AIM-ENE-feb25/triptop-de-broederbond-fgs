package nl.han.soex.prototype.transport;

public class TripRequest {

    private String departure;
    private String destination;
    private String date;
    private TransportType transportType;

    public TripRequest(String departure, String destination, String date, TransportType transportType) {
        this.departure = departure;
        this.destination = destination;
        this.date = date;
        this.transportType = transportType;
    }

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
