package com.avajlauncher.weather;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    public int getLongitude() {
        return this.longitude;
    }

    public int getLatitude() {
        return this.latitude;
    }

    public int getHeight() {
        return this.height;
    }
    
    public Coordinates(int longitude, int latitude, int height) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
    }

    public String toString() {
        return (String.format("Lat: %s, Long: %s, Height: %s", this.latitude, this.longitude, this.height));
    }
}