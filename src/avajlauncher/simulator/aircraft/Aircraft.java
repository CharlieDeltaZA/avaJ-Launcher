package simulator.aircraft;

public abstract class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter;    

    protected void Aircraft(String name, Coordinates coordinates) {
        // ???? 
    } 

    private long nextId() {
        
    }
}