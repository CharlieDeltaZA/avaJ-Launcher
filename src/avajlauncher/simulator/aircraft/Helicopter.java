package simulator.aircraft;

public class Helicopter implements Flyable {

    private WeatherTower weatherTower;

    @Override
    public void updateConditions() {
        System.out.println("Helicopter updateConditions");
    }

    @Override
    public void registerTower(WeatherTower WeatherTower) {
        System.out.println("Helicopter registerTower");
    }
    
}