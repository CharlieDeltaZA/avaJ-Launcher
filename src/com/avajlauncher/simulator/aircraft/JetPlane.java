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
        // System.out.println("JetPlane updateConditions");
        String wx = this.weatherTower.getWeather(this.coordinates);
        String msg = "";

        switch (wx) {
            case "SUN":
                System.out.println("Old Coords: " + coordinates.getLatitude() + " " + coordinates.getLongitude() + " " + coordinates.getHeight());
                this.coordinates = new Coordinates(coordinates.getLongitude() + 0, coordinates.getLatitude() + 10, coordinates.getHeight() + 2);
                System.out.println("New Coords: " + coordinates.getLatitude() + " " + coordinates.getLongitude() + " " + coordinates.getHeight());
                msg = "What a wonderful day!";
                break;
            case "RAIN":
                System.out.println("Old Coords: " + coordinates.getLatitude() + " " + coordinates.getLongitude() + " " + coordinates.getHeight());
                this.coordinates = new Coordinates(coordinates.getLongitude() + 0, coordinates.getLatitude() + 7, coordinates.getHeight() + 0);
                System.out.println("New Coords: " + coordinates.getLatitude() + " " + coordinates.getLongitude() + " " + coordinates.getHeight());
                msg = "Wonder if we'll be hit by lightning?";
                break;
            case "FOG":
                System.out.println("Old Coords: " + coordinates.getLatitude() + " " + coordinates.getLongitude() + " " + coordinates.getHeight());
                this.coordinates = new Coordinates(coordinates.getLongitude() + 0, coordinates.getLatitude() + 1, coordinates.getHeight() + 0);
                System.out.println("New Coords: " + coordinates.getLatitude() + " " + coordinates.getLongitude() + " " + coordinates.getHeight());
                msg = "Why is there a mountain goat in front of us?";
                break;
            case "SNOW":
                System.out.println("Old Coords: " + coordinates.getLatitude() + " " + coordinates.getLongitude() + " " + coordinates.getHeight());
                this.coordinates = new Coordinates(coordinates.getLongitude() + 0, coordinates.getLatitude() + 0, coordinates.getHeight() - 7);
                System.out.println("New Coords: " + coordinates.getLatitude() + " " + coordinates.getLongitude() + " " + coordinates.getHeight());
                msg = "Time to turn on the pitot heat.";
                break;
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