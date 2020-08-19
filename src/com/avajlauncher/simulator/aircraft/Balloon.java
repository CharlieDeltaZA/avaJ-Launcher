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
        // System.out.println("Balloon updateConditions");
        String wx = this.weatherTower.getWeather(this.coordinates);
        String msg = "";

        switch (wx) {
            case "SUN":
                this.coordinates = new Coordinates(coordinates.getLongitude() + 2, coordinates.getLatitude() + 0, coordinates.getHeight() + 4);
                msg = "Pleasant day for some looning!";
            case "RAIN":
                this.coordinates = new Coordinates(coordinates.getLongitude() + 0, coordinates.getLatitude() + 0, coordinates.getHeight() - 5);
                msg = "Are we getting heavier?";
            case "FOG":
                this.coordinates = new Coordinates(coordinates.getLongitude() + 0, coordinates.getLatitude() + 0, coordinates.getHeight() - 3);
                msg = "Which direction is the ground?";
            case "SNOW":
                this.coordinates = new Coordinates(coordinates.getLongitude() + 0, coordinates.getLatitude() + 0, coordinates.getHeight() - 15);
                msg = "It's cold, turn up the burner.";
        }

        if (this.coordinates.getHeight() <= 0) {
            System.out.println(this.getClass().getSimpleName() + "#" + this.name + "(" + this.id +"): " + msg);
            System.out.println(this.getClass().getSimpleName() + "#" + this.name + "(" + this.id +"): Landing. Lat: " + coordinates.getLatitude() + " Long: " + coordinates.getLongitude() + " Height: " + coordinates.getHeight());
            System.out.println("Tower says: " + this.getClass().getSimpleName() + "#" + this.name + "(" + this.id +") unregistered from weather tower." );
            this.weatherTower.unregister(this);
        } else {
            System.out.println(this.getClass().getSimpleName() + "#" + this.name + "(" + this.id +"): " + msg);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        System.out.println("Tower says: " + this.getClass().getSimpleName() + "#" + this.name + "(" + this.id +") registered to weather tower." );
    }

    // TODO: Remove below
    @Override
    public void testing() {
        this.weatherTower.unregister(this);
        System.out.println("Tower says: " + this.getClass().getSimpleName() + "#" + this.name + "(" + this.id +") unregistered from weather tower." );
    }
}