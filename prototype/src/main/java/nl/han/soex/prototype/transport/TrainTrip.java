package nl.han.soex.prototype.transport;

public class TrainTrip extends Trip {
    private Long tripId;
    private String plannedTrack;
    private String actualTrack;

    public TrainTrip(Long tripId, String departure, String destination, String date,
                     String plannedTrack, String actualTrack) {
        super(departure, destination, date);
        this.tripId = tripId;
        this.plannedTrack = plannedTrack;
        this.actualTrack = actualTrack;
    }

    // Getters and Setters
    public Long getTripId() { return tripId; }
    public void setTripId(Long tripId) { this.tripId = tripId; }

    public String getPlannedTrack() { return plannedTrack; }
    public void setPlannedTrack(String plannedTrack) { this.plannedTrack = plannedTrack; }

    public String getActualTrack() { return actualTrack; }
    public void setActualTrack(String actualTrack) { this.actualTrack = actualTrack; }

}
