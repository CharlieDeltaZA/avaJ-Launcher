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
        // System.out.println("Balloon updateConditions");
        String wx = this.weatherTower.getWeather(this.coordinates);
        String msg = "";

        switch (wx) {
            case "SUN":
                // System.out.println("Old Coords: " + coordinates.getLatitude() + " " + coordinates.getLongitude() + " " + coordinates.getHeight());
                this.coordinates = new Coordinates(coordinates.getLongitude() + 2, coordinates.getLatitude() + 0, coordinates.getHeight() + 4);
                // System.out.println("New Coords: " + coordinates.getLatitude() + " " + coordinates.getLongitude() + " " + coordinates.getHeight());
                msg = "Pleasant day for some looning!";
                break;
            case "RAIN":
                // System.out.println("Old Coords: " + coordinates.getLatitude() + " " + coordinates.getLongitude() + " " + coordinates.getHeight());
                this.coordinates = new Coordinates(coordinates.getLongitude() + 0, coordinates.getLatitude() + 0, coordinates.getHeight() - 5);
                // System.out.println("New Coords: " + coordinates.getLatitude() + " " + coordinates.getLongitude() + " " + coordinates.getHeight());
                msg = "Are we getting heavier?";
                break;
            case "FOG":
                // System.out.println("Old Coords: " + coordinates.getLatitude() + " " + coordinates.getLongitude() + " " + coordinates.getHeight());
                this.coordinates = new Coordinates(coordinates.getLongitude() + 0, coordinates.getLatitude() + 0, coordinates.getHeight() - 3);
                // System.out.println("New Coords: " + coordinates.getLatitude() + " " + coordinates.getLongitude() + " " + coordinates.getHeight());
                msg = "Which direction is the ground?";
                break;
            case "SNOW":
                // System.out.println("Old Coords: " + coordinates.getLatitude() + " " + coordinates.getLongitude() + " " + coordinates.getHeight());
                this.coordinates = new Coordinates(coordinates.getLongitude() + 0, coordinates.getLatitude() + 0, coordinates.getHeight() - 15);
                // System.out.println("New Coords: " + coordinates.getLatitude() + " " + coordinates.getLongitude() + " " + coordinates.getHeight());
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
        // else {
        // }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        logger.addLine("Tower says: " + this.getClass().getSimpleName() + "#" + this.name + "(" + this.id +") registered to weather tower.\n");
        // System.out.println("Tower says: " + this.getClass().getSimpleName() + "#" + this.name + "(" + this.id +") registered to weather tower.");
    }
}