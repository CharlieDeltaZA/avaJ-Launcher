package com.avajlauncher.simulator.aircraft;

import com.avajlauncher.simulator.Logger;
import com.avajlauncher.simulator.WeatherTower;
import com.avajlauncher.weather.Coordinates;

public class Helicopter extends Aircraft implements Flyable {

    private Logger logger = Logger.getLogger();
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
        String wx = this.weatherTower.getWeather(this.coordinates);
        String msg = "";

        switch (wx) {
            case "SUN":
                this.coordinates = new Coordinates(coordinates.getLatitude() + 0, coordinates.getLongitude() + 10, coordinates.getHeight() + 2);
                msg = "Lets go find some points of interest!";
                break;
            case "RAIN":
                this.coordinates = new Coordinates(coordinates.getLatitude() + 0, coordinates.getLongitude() + 5, coordinates.getHeight() + 0);
                msg = "Is there a leak back there?";
                break;
            case "FOG":
                this.coordinates = new Coordinates(coordinates.getLatitude() + 0, coordinates.getLongitude() + 1, coordinates.getHeight() + 0);
                msg = "Where did that mountain go?";
                break;
            case "SNOW":
                this.coordinates = new Coordinates(coordinates.getLatitude() + 0, coordinates.getLongitude() + 0, coordinates.getHeight() - 12);
                msg = "Hopefully the spinny bits don't freeze.";
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