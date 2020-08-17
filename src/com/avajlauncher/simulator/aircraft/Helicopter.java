package com.avajlauncher.simulator.aircraft;

import com.avajlauncher.simulator.WeatherTower;
import com.avajlauncher.weather.Coordinates;

public class Helicopter extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    /*  Helicopter Weather (Lat, Long, Height)
    **  SUN  :                *,  +10,   +2
    **  RAIN :                *,   +5,   *
    **  FOG  :                *,   +1,   *
    **  SNOW :                *,    *,   -12
    */

    @Override
    public void updateConditions() {
        System.out.println("Helicopter updateConditions");
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        System.out.println("Helicopter registerTower");
        this.weatherTower = weatherTower;
    }
    
}