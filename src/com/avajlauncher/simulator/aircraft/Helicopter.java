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
        // System.out.println("Helicopter updateConditions");
        String wx = this.weatherTower.getWeather(this.coordinates);
        String msg = "";

        switch (wx) {
            case "SUN":
                System.out.println("Old Coords: " + coordinates.getLatitude() + " " + coordinates.getLongitude() + " " + coordinates.getHeight());
                this.coordinates = new Coordinates(coordinates.getLongitude() + 10, coordinates.getLatitude() + 0, coordinates.getHeight() + 2);
                System.out.println("New Coords: " + coordinates.getLatitude() + " " + coordinates.getLongitude() + " " + coordinates.getHeight());
                msg = "Lets go find some points of interest!";
                break;
            case "RAIN":
                System.out.println("Old Coords: " + coordinates.getLatitude() + " " + coordinates.getLongitude() + " " + coordinates.getHeight());
                this.coordinates = new Coordinates(coordinates.getLongitude() + 5, coordinates.getLatitude() + 0, coordinates.getHeight() + 0);
                System.out.println("New Coords: " + coordinates.getLatitude() + " " + coordinates.getLongitude() + " " + coordinates.getHeight());
                msg = "Is there a leak back there?";
                break;
            case "FOG":
                System.out.println("Old Coords: " + coordinates.getLatitude() + " " + coordinates.getLongitude() + " " + coordinates.getHeight());
                this.coordinates = new Coordinates(coordinates.getLongitude() + 1, coordinates.getLatitude() + 0, coordinates.getHeight() + 0);
                System.out.println("New Coords: " + coordinates.getLatitude() + " " + coordinates.getLongitude() + " " + coordinates.getHeight());
                msg = "Where did that mountain go?";
                break;
            case "SNOW":
                System.out.println("Old Coords: " + coordinates.getLatitude() + " " + coordinates.getLongitude() + " " + coordinates.getHeight());
                this.coordinates = new Coordinates(coordinates.getLongitude() + 0, coordinates.getLatitude() + 0, coordinates.getHeight() - 12);
                System.out.println("New Coords: " + coordinates.getLatitude() + " " + coordinates.getLongitude() + " " + coordinates.getHeight());
                msg = "Hopefully the spinny bits don't freeze.";
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
}