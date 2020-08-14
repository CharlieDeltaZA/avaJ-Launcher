package com.avajlauncher.simulator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Simulator {

    public static void main(String[] args) {
        String filename = args[0];
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            System.out.println(line);
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please specify a simulation file.");
        } catch (FileNotFoundException e) {
            System.out.println("Could not find simulation file '" + filename + "'");
        } catch (IOException e) {
            System.out.println("Error while reading file '" + filename + "'");
        }
    }
    
}