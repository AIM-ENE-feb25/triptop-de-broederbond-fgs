package persoonlijkeUitwerkingen.CompositionDemo.Inheritance;

public class Car extends Engine {
    public Car() {
    }

    // set engine kan niet

    // naam kan niet start zijn
    public void startCar() {
        start();
    }

    // naam kan niet stop zijn
    public void stopCar() {
        stop();
    }
}
