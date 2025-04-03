package nl.han.soex.prototype.transport;

public class Trip {
    private Long tripId;
    private String departure;
    private String destination;
    private String date;
    private String plannedTrack;
    private String actualTrack;

    public Trip(Long tripId, String departure, String destination, String date, String plannedTrack, String actualTrack) {
        this.tripId = tripId;
        this.departure = departure;
        this.destination = destination;
        this.date = date;
        this.plannedTrack = plannedTrack;
        this.actualTrack = actualTrack;
    }

    // Getters and Setters
    public String getPlannedTrack() { return plannedTrack; }
    public void setPlannedTrack(String plannedTrack) { this.plannedTrack = plannedTrack; }

    public String getActualTrack() { return actualTrack; }
    public void setActualTrack(String actualTrack) { this.actualTrack = actualTrack; }
}
