package com.avajlauncher.simulator;

public class Simulator {

    public static void main(String[] args) throws InterruptedException {
        try {
            System.out.println(args[0]);
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please specify a simulation file.");
        }
    }
    
}