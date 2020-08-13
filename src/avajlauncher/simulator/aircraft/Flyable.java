package simulator.aircraft;

public interface Flyable {
    public void updateConditions();
    public void registerTower(WeatherTower weatherTower);
}