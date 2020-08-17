package com.avajlauncher.simulator.aircraft;

import com.avajlauncher.simulator.WeatherTower;

public interface Flyable {
    public void updateConditions();
    public void registerTower(WeatherTower weatherTower);
    // TODO: Remove below
    public void testing();
}