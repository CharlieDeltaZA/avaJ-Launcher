package simulator.aircraft;

import simulator.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {

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