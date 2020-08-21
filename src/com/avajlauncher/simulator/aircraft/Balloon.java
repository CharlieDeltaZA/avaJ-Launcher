package com.avajlauncher.simulator.aircraft;

import com.avajlauncher.simulator.Logger;
import com.avajlauncher.simulator.WeatherTower;
import com.avajlauncher.weather.Coordinates;

public class Balloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;
    private Logger logger = Logger.getLogger();

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

        switch (wx) {
            case "SUN":
                this.coordinates = new Coordinates(coordinates.getLatitude() + 0, coordinates.getLongitude() + 2, coordinates.getHeight() + 4);
                msg = "Pleasant day for some looning!";
                break;
            case "RAIN":
                this.coordinates = new Coordinates(coordinates.getLatitude() + 0, coordinates.getLongitude() + 0, coordinates.getHeight() - 5);
                msg = "Are we getting heavier?";
                break;
            case "FOG":
                this.coordinates = new Coordinates(coordinates.getLatitude() + 0, coordinates.getLongitude() + 0, coordinates.getHeight() - 3);
                msg = "Which direction is the ground?";
                break;
            case "SNOW":
                this.coordinates = new Coordinates(coordinates.getLatitude() + 0, coordinates.getLongitude() + 0, coordinates.getHeight() - 15);
                msg = "It's cold, turn up the burner.";
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