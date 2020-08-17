package com.avajlauncher.simulator.aircraft;

import com.avajlauncher.simulator.WeatherTower;
import com.avajlauncher.weather.Coordinates;

public class Balloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Balloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }
    
    /*  Balloon Weather (Lat, Long, Height)
    **  SUN  :             *,   +2,    +4
    **  RAIN :             *,    *,    -5
    **  FOG  :             *,    *,    -3
    **  SNOW :             *,    *,    -15
    */

    @Override
    public void updateConditions() {
        System.out.println("Balloon updateConditions");
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        System.out.println("Balloon registerTower");
        this.weatherTower = weatherTower;
    }
}