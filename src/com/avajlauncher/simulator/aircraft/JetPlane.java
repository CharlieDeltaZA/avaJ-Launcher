package com.avajlauncher.simulator.aircraft;

import com.avajlauncher.simulator.Logger;
import com.avajlauncher.simulator.WeatherTower;
import com.avajlauncher.weather.Coordinates;

public class JetPlane extends Aircraft implements Flyable {

    private Logger logger = Logger.getLogger();
    private WeatherTower weatherTower;
    private String classSimpleName = this.getClass().getSimpleName();

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
        String wx = this.weatherTower.getWeather(this.coordinates);
        String msg = "";
        int height = coordinates.getHeight();

        switch (wx) {
            case "SUN":
                if ((height + 2) > 100) {
                    height = 100;
                }
                this.coordinates = new Coordinates(coordinates.getLatitude() + 10, coordinates.getLongitude() + 0, height + 2);
                msg = "What a wonderful day!";
                break;
            case "RAIN":
                this.coordinates = new Coordinates(coordinates.getLatitude() + 7, coordinates.getLongitude() + 0, height + 0);
                msg = "Wonder if we'll be hit by lightning?";
                break;
            case "FOG":
                this.coordinates = new Coordinates(coordinates.getLatitude() + 1, coordinates.getLongitude() + 0, height + 0);
                msg = "Why is there a mountain goat in front of us?";
                break;
            case "SNOW":
                this.coordinates = new Coordinates(coordinates.getLatitude() + 0, coordinates.getLongitude() + 0, height - 7);
                msg = "Time to turn on the pitot heat.";
                break;
        }

        logger.addLine(String.format("%s#%s(%s): %s\n", classSimpleName, this.name, this.id, msg));
        // System.out.println(classSimpleName + "#" + this.name + "(" + this.id + "): " + msg);
        
        if (this.coordinates.getHeight() <= 0) {
            // System.out.println(classSimpleName + "#" + this.name + "(" + this.id +"): " + msg);
            // System.out.println(classSimpleName + "#" + this.name + "(" + this.id +"): Landing... Lat: " + coordinates.getLatitude() + " Long: " + coordinates.getLongitude() + " Height: " + coordinates.getHeight());
            // System.out.println("Tower says: " + classSimpleName + "#" + this.name + "(" + this.id +") unregistered from weather tower." );
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
        // System.out.println("Tower says: " + classSimpleName + "#" + this.name + "(" + this.id +") registered to weather tower.");
    }
}