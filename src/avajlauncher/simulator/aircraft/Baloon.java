package simulator.aircraft;

public class Baloon implements Flyable {

    private WeatherTower weatherTower;
    
    @Override
    public void updateConditions() {
        System.out.println("Baloon updateConditions");
    }

    @Override
    public void registerTower(WeatherTower WeatherTower) {
        System.out.println("Baloon registerTower");
    }
}