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
            int simCount = -1;

            if (line != null) {
                try {
                    simCount = Integer.parseInt(line);

                    if (simCount <= 0) {
                        System.out.println("Invalid simulation count. Must be greater than 0.");
                        System.exit(1);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("First line must be a positive number.");
                    System.exit(1);
                }

                // Line Format
                // TYPE NAME LONG LAT HEIGHT <- Lat & Long should realistically be switched but w/e
                while ((line = reader.readLine()) != null) {
                    String[] splitLine = line.split(" ");

                    if (splitLine.length != 5) {
                        System.out.println("Missing parameter: Please ensure Type, Name, Long, Lat & Height are present in the following line:");
                        System.out.println(line);
                        System.exit(1);
                    }

                    try {
                        Flyable aircraft = AircraftFactory.newAircraft(splitLine[0], splitLine[1],
                                        Integer.parseInt(splitLine[2]), Integer.parseInt(splitLine[3]), Integer.parseInt(splitLine[4]));
                        
                        hangar.add(aircraft);

                    } catch (NumberFormatException e) {
                        System.out.println("Please ensure all coordinates are numerical in the following line:");
                        System.out.println(line);
                        System.exit(1);
                    }

                }

                // Finished with the reader, close it.
                reader.close();
                
                for (Flyable aircraft : hangar) {
                    aircraft.registerTower(weatherTower);
                }

                for (int i = 1; i <= simCount; i++) {
                    weatherTower.changeWeather();
                }

                // Log output to the file.
                logger.saveFile();
            }
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please specify a scenario file.");
        } catch (FileNotFoundException e) {
            System.out.println(String.format("Could not find scenario file '%s'", args[0]));
        } catch (IOException e) {
            System.out.println(String.format("Error while reading file '%s'", args[0]));
        }
    }
    
}