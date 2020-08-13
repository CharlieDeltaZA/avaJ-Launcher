package simulator.aircraft;

public class JetPlane implements Flyable {

    private WeatherTower weatherTower;

    @Override
    public void updateConditions() {
        System.out.println("JetPlane updateConditions");
    }

    @Override
    public void registerTower(WeatherTower WeatherTower) {
        System.out.println("JetPlane registerTower");
    }
    
}