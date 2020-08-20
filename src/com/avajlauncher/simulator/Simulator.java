package com.avajlauncher.simulator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.avajlauncher.simulator.aircraft.AircraftFactory;
import com.avajlauncher.simulator.aircraft.Flyable;

public class Simulator {

    private static WeatherTower weatherTower = new WeatherTower();
    private static List<Flyable> hangar = new ArrayList<>();
    private static Logger logger = Logger.getLogger();

    public static void main(String[] args) {
        
        try {
            String filename = args[0];
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            if (line != null) {
                // System.out.println("Line one: " + line); // Debug
                int simCount = Integer.parseInt(line); // line.split(" ")[0] ??
                // TODO: Handle first line being NOT numbers
                if (simCount <= 0) {
                    System.out.println("Invalid simulation count. Must be > 0.");
                    System.exit(1);
                }

                // Line Format
                // TYPE NAME LAT LONG HEIGHT
                while ((line = reader.readLine()) != null) {
                    // System.out.println(line); // debug
                    // TODO: Deal with unrecognized types - Custom Exceptions, better protection of split inputs?
                    Flyable aircraft = AircraftFactory.newAircraft(line.split(" ")[0], line.split(" ")[1],
                                    Integer.parseInt(line.split(" ")[2]), Integer.parseInt(line.split(" ")[3]), Integer.parseInt(line.split(" ")[4]));
                    hangar.add(aircraft);
                }

                for (Flyable aircraft : hangar) {
                    // System.out.println(aircraft);
                    aircraft.registerTower(weatherTower);
                }
                // OI BRUH - Handle NEGATIVE COORDS PROPERLY. ADD MORE EXCEPTIONS. REMEMBER JADON's MISTAKE :)

                for (int i = 1; i <= simCount; i++) {
                    // System.out.println("Changing wx: " + i);
                    weatherTower.changeWeather();
                }

                logger.saveFile();
            }

            reader.close();
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please specify a simulation file.");
        } catch (FileNotFoundException e) {
            System.out.println("Could not find simulation file '" + args[0] + "'");
        } catch (IOException e) {
            System.out.println("Error while reading file '" + args[0] + "'");
        }
    }
    
}