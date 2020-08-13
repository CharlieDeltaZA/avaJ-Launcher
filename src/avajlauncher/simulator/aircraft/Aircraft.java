package simulator.aircraft;

import weather.Coordinates;

public abstract class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter;    

    protected Aircraft(String name, Coordinates coordinates) {
        // ???? 
    } 

    private long nextId() {
        
    }
}