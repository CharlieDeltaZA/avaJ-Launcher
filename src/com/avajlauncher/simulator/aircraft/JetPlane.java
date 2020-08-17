package com.avajlauncher.simulator.aircraft;

import com.avajlauncher.simulator.WeatherTower;
import com.avajlauncher.weather.Coordinates;

public class JetPlane extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    /*  JetPlane Weather (Lat, Long, Height)
    **  SUN  :            +10,    *,   +2
    **  RAIN :             +7,    *,   *
    **  FOG  :             +1,    *,   *
    **  SNOW :              *,    *,   -7
    */

    @Override
    public void updateConditions() {
        System.out.println("JetPlane updateConditions");
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        System.out.println("JetPlane registerTower");
        this.weatherTower = weatherTower;
    }
    
}