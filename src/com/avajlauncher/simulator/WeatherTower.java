package com.avajlauncher.simulator;

import com.avajlauncher.weather.Coordinates;
import com.avajlauncher.weather.WeatherProvider;

public class WeatherTower extends Tower {
    public String getWeather(Coordinates coordinates) {
        String wx = WeatherProvider.getProvider().getCurrentWeather(coordinates);
        return (wx);
    }

    public void changeWeather() {
        this.conditionsChanged();
    }
}