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
        System.out.println("now: " +now);
        int seed = new Random(now).nextInt();
        System.out.println("initial seed: " +seed);
        System.out.println(longitude*seed);
        seed = new Random(longitude * seed).nextInt();
        System.out.println("seed+long: " +seed);
        seed = new Random(seed + latitude).nextInt();
        System.out.println("seed+lat: " +seed);
        seed = new Random(height - seed).nextInt();
        System.out.println("seed-height: " +seed);
        index = new Random(seed).nextInt(4);
        System.out.println("seed .nextInt(4): " +seed);
        System.out.println("index: " +index);
        return weather[index];
    }
}