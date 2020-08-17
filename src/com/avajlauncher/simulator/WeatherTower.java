package com.avajlauncher.simulator;

import com.avajlauncher.weather.Coordinates;
import com.avajlauncher.weather.WeatherProvider;

public class WeatherTower extends Tower {
    public String getWeatherConditions(Coordinates coordinates) {
        // ????
        String wx = WeatherProvider.getProvider().getCurrentWeather(coordinates);
        return wx;
    }

    void changeWeather() {
        this.conditionsChanged();
    }
}