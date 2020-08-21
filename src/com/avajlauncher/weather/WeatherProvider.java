package com.avajlauncher.weather;

import java.util.Date;
import java.util.Random;

public class WeatherProvider {
    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static String[] weather = {"SUN", "RAIN", "FOG", "SNOW"};

    private WeatherProvider() {
        
    }

    public static WeatherProvider getProvider() {
        return (weatherProvider);
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int longitude = coordinates.getLongitude();
        int latitude = coordinates.getLatitude();
        int height = coordinates.getHeight();
        
        int index = 0;
        int now = (int) new Date().getTime();
        int seed = new Random(now).nextInt();

        seed = new Random(longitude * seed).nextInt();
        seed = new Random(seed / latitude).nextInt();
        seed = new Random(height - seed).nextInt();
        index = new Random(seed).nextInt(4);

        return (weather[index]);
    }
}