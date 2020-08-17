package com.avajlauncher.simulator;

import java.util.ArrayList;
import java.util.List;

import com.avajlauncher.simulator.aircraft.Flyable;

public abstract class Tower {
    private List<Flyable> observers = new ArrayList<Flyable>();
    private List<Flyable> landed = new ArrayList<Flyable>();

    public void register(Flyable flyable) {
        if (!observers.contains(flyable)) {
            observers.add(flyable);
        }
    }

    public void unregister(Flyable flyable) {
        if (!landed.contains(flyable)) {
            landed.add(flyable);
        }
    }

    protected void conditionsChanged() {
        
    }
    
}