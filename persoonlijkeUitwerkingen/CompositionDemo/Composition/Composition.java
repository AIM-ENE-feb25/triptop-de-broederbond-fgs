package persoonlijkeUitwerkingen.CompositionDemo.Composition;

public class Composition {
    public static void main(String[] args) {
        Car car = new Car();
        car.setEngine(new Engine());

        car.start();
        car.stop();

        car.setEngine(new Engine());
    }
}
