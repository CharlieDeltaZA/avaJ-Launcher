package com.avajlauncher.simulator.aircraft;

import com.avajlauncher.weather.Coordinates;

public abstract class AircraftFactory {
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {

        Coordinates coords = new Coordinates(longitude, latitude, height);

        switch (type) {
            case "JetPlane":
                return (new JetPlane(name, coords));
            case "Helicopter":
                return (new Helicopter(name, coords));
            case "Balloon":
                return (new Balloon(name, coords));
            default:
                System.out.println(String.format("Aircraft type '%s' not recognized. Aborting takeoff...", type));
                System.exit(1);
        }

        return (null);
    }
    
}