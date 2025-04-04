package nl.han.soex.prototype.transport;

public class FlightTrip extends Trip {
    private double price; // Price for the flight

    public FlightTrip(String departure, String destination, String date, double price) {
        super(departure, destination, date);
        this.price = price;
    }

    // Getter and Setter for price
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
