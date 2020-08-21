package com.avajlauncher.simulator.aircraft;

import com.avajlauncher.simulator.Logger;
import com.avajlauncher.simulator.WeatherTower;
import com.avajlauncher.weather.Coordinates;

public class JetPlane extends Aircraft implements Flyable {

    private Logger logger = Logger.getLogger();
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
        String wx = this.weatherTower.getWeather(this.coordinates);
        String msg = "";

        switch (wx) {
            case "SUN":
                this.coordinates = new Coordinates(coordinates.getLatitude() + 10, coordinates.getLongitude() + 0, coordinates.getHeight() + 2);
                msg = "What a wonderful day!";
                break;
            case "RAIN":
                this.coordinates = new Coordinates(coordinates.getLatitude() + 7, coordinates.getLongitude() + 0, coordinates.getHeight() + 0);
                msg = "Wonder if we'll be hit by lightning?";
                break;
            case "FOG":
                this.coordinates = new Coordinates(coordinates.getLatitude() + 1, coordinates.getLongitude() + 0, coordinates.getHeight() + 0);
                msg = "Why is there a mountain goat in front of us?";
                break;
            case "SNOW":
                this.coordinates = new Coordinates(coordinates.getLatitude() + 0, coordinates.getLongitude() + 0, coordinates.getHeight() - 7);
                msg = "Time to turn on the pitot heat.";
                break;
        }

        logger.addLine(this.getClass().getSimpleName() + "#" + this.name + "(" + this.id +"): " + msg + "\n");
        // System.out.println(this.getClass().getSimpleName() + "#" + this.name + "(" + this.id +"): " + msg);
        
        if (this.coordinates.getHeight() <= 0) {
            // System.out.println(this.getClass().getSimpleName() + "#" + this.name + "(" + this.id +"): " + msg);
            logger.addLine(this.getClass().getSimpleName() + "#" + this.name + "(" + this.id + "): Landing... (" + coordinates.toString() + ")\n");
            // System.out.println(this.getClass().getSimpleName() + "#" + this.name + "(" + this.id +"): Landing... Lat: " + coordinates.getLatitude() + " Long: " + coordinates.getLongitude() + " Height: " + coordinates.getHeight());
            logger.addLine("Tower says: " + this.getClass().getSimpleName() + "#" + this.name + "(" + this.id +") unregistered from weather tower.\n");
            // System.out.println("Tower says: " + this.getClass().getSimpleName() + "#" + this.name + "(" + this.id +") unregistered from weather tower." );
            this.weatherTower.unregister(this);
        } 
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        logger.addLine("Tower says: " + this.getClass().getSimpleName() + "#" + this.name + "(" + this.id +") registered to weather tower.\n");
        // System.out.println("Tower says: " + this.getClass().getSimpleName() + "#" + this.name + "(" + this.id +") registered to weather tower.");
    }
}