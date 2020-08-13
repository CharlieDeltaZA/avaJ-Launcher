package simulator.aircraft;

import simulator.WeatherTower;

public class Balloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;
    
    @Override
    public void updateConditions() {
        System.out.println("Balloon updateConditions");
    }

    @Override
    public void registerTower(WeatherTower WeatherTower) {
        System.out.println("Balloon registerTower");
    }
}