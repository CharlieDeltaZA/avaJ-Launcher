package com.avajlauncher.simulator;

import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    public static String content = "";
    private static Logger logger = new Logger();

    private Logger() {

    }

    public static Logger getLogger() {
        return logger;
    }

    // File file
    public void addLine(String msg) {
        content = content + msg;
    }
    
    public void saveFile() {
        try {
            FileWriter writer = new FileWriter("simulation.txt");
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            //TODO: handle exception
            System.out.println(e);
        }
        
    }
    
}