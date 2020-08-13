package simulator.aircraft;

import simulator.WeatherTower;

public class JetPlane extends Aircraft implements Flyable {

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