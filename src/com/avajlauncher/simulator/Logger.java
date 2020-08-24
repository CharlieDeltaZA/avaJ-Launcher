package com.avajlauncher.simulator;

import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    private static String content = "";
    private static Logger logger = new Logger();

    private Logger() {

    }

    public static Logger getLogger() {
        return logger;
    }

    public void addLine(String line) {
        content = content + line;
    }
    
    public void saveFile() {
        try {
            FileWriter writer = new FileWriter("simulation.txt");
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error creating file 'simulation.txt', quitting...");
            System.out.println(e);
            System.exit(1);
        }
        
    }
    
}