package com.avajlauncher.simulator.aircraft;

import com.avajlauncher.weather.Coordinates;

public abstract class AircraftFactory {
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {

        // Coordinates are positive numbers
        if (longitude <= 0 || latitude <= 0 || height <= 0) {
            System.out.println(String.format("%s#%s - Coordinates must be positive numbers.", type, name));
            System.exit(1);
        }

        // Height is in the 0-100 range
        if (height > 100) {
            System.out.println(String.format("%s#%s - Height must be between 0 and 100.", type, name));
            System.exit(1);
        }

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