package com.avajlauncher.simulator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Simulator {

    public static void main(String[] args) {
        
        try {
            String filename = args[0];
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            if (line != null) {
                System.out.println("Line one: " + line); // Debug
                int simCount = Integer.parseInt(line); // line.split(" ")[0] ??
                // TODO: Handle first line being NOT numbers
                if (simCount <= 0) {
                    System.out.println("Invalid simulation count. Must be > 0.");
                    System.exit(1);
                }

                // Line Format
                // TYPE NAME LAT LONG HEIGHT
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please specify a simulation file.");
        } catch (FileNotFoundException e) {
            System.out.println("Could not find simulation file '" + args[0] + "'");
        } catch (IOException e) {
            System.out.println("Error while reading file '" + args[0] + "'");
        }
    }
    
}