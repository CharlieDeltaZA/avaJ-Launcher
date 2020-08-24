package com.avajlauncher.simulator.aircraft;

import com.avajlauncher.simulator.Logger;
import com.avajlauncher.simulator.WeatherTower;
import com.avajlauncher.weather.Coordinates;

public class Helicopter extends Aircraft implements Flyable {

    private Logger logger = Logger.getLogger();
    private WeatherTower weatherTower;
    private String classSimpleName = this.getClass().getSimpleName();

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
        String wx = this.weatherTower.getWeather(this.coordinates);
        String msg = "";
        int height = coordinates.getHeight();

        switch (wx) {
            case "SUN":
                if ((height + 2) > 100) {
                    height = 100;
                } else {
                    height += 2;
                }
                this.coordinates = new Coordinates(coordinates.getLatitude() + 0, coordinates.getLongitude() + 10, height);
                msg = "Lets go find some points of interest!";
                break;
            case "RAIN":
                this.coordinates = new Coordinates(coordinates.getLatitude() + 0, coordinates.getLongitude() + 5, height + 0);
                msg = "Is there a leak back there?";
                break;
            case "FOG":
                this.coordinates = new Coordinates(coordinates.getLatitude() + 0, coordinates.getLongitude() + 1, height + 0);
                msg = "Where did that mountain go?";
                break;
            case "SNOW":
                this.coordinates = new Coordinates(coordinates.getLatitude() + 0, coordinates.getLongitude() + 0, height - 12);
                msg = "Hopefully the spinny bits don't freeze.";
                break;
        }

        logger.addLine(String.format("%s#%s(%s): %s\n", classSimpleName, this.name, this.id, msg));
        // System.out.println(String.format("%s#%s(%s): %s", classSimpleName, this.name, this.id, msg));
        
        if (this.coordinates.getHeight() <= 0) {
            // System.out.println(String.format("%s#%s(%s): Landing... (%s)", classSimpleName, this.name, this.id, coordinates.toString()));
            // System.out.println(String.format("Tower says: %s#%s(%s) unregistered from weather tower.", classSimpleName, this.name, this.id));
            logger.addLine(String.format("%s#%s(%s): Landing... (%s)\n", classSimpleName, this.name, this.id, coordinates.toString()));
            logger.addLine(String.format("Tower says: %s#%s(%s) unregistered from weather tower.\n", classSimpleName, this.name, this.id));
            this.weatherTower.unregister(this);
        } 
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        logger.addLine(String.format("Tower says: %s#%s(%s) registered to weather tower.\n", classSimpleName, this.name, this.id));
        // System.out.println(String.format("Tower says: %s#%s(%s) registered to weather tower.", classSimpleName, this.name, this.id));
    }
}