package com.avajlauncher.simulator.aircraft;

import com.avajlauncher.simulator.Logger;
import com.avajlauncher.simulator.WeatherTower;
import com.avajlauncher.weather.Coordinates;

public class Balloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;
    private Logger logger = Logger.getLogger();
    private String classSimpleName = this.getClass().getSimpleName();

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
        String wx = this.weatherTower.getWeather(this.coordinates);
        String msg = "";
        int height = coordinates.getHeight();

        switch (wx) {
            case "SUN":
                if ((height + 4) > 100) {
                    height = 100;
                } else {
                    height += 4;
                }
                this.coordinates = new Coordinates(coordinates.getLatitude() + 0, coordinates.getLongitude() + 2, height);
                msg = "Pleasant day for some looning!";
                break;
            case "RAIN":
                this.coordinates = new Coordinates(coordinates.getLatitude() + 0, coordinates.getLongitude() + 0, height - 5);
                msg = "Are we getting heavier?";
                break;
            case "FOG":
                this.coordinates = new Coordinates(coordinates.getLatitude() + 0, coordinates.getLongitude() + 0, height - 3);
                msg = "Which direction is the ground?";
                break;
            case "SNOW":
                this.coordinates = new Coordinates(coordinates.getLatitude() + 0, coordinates.getLongitude() + 0, height - 15);
                msg = "It's cold, turn up the burner.";
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