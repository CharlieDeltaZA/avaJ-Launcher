package com.avajlauncher.simulator;

import com.avajlauncher.simulator.aircraft.Flyable;

public abstract class Tower {
    private Flyable observers;

    public void register(Flyable flyable) {
        
    }

    public void unregister(Flyable flyable) {

    }

    protected void conditionsChanged() {
        
    }
    
}