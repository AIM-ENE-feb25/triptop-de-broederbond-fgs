package persoonlijkeUitwerkingen.CompositionDemo.Composition;

public class Car {
    Engine engine;

    public Car() {
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void start() {
        engine.start();
    }

    public void stop() {
        engine.stop();
    }
}
