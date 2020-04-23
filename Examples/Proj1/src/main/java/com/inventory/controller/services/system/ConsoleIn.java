package com.inventory.controller.services.system;

import java.util.Scanner;

public class ConsoleIn {
    private ConsoleIn(){}

    private static ConsoleIn consoleIn;
    private Scanner scanner;

    public static ConsoleIn getInstance(){
        if(consoleIn == null)
            consoleIn = new ConsoleIn();
        return consoleIn;
    }

    public String next(){
        if(scanner == null)
            scanner = IO.getSystemScanner();
        return scanner.next();
    }

    public int nextInt(){
        if(scanner == null)
            scanner = IO.getSystemScanner();
        return scanner.nextInt();
    }

    public double nextDouble(){
        if(scanner == null)
            scanner = IO.getSystemScanner();
        return scanner.nextDouble();
    }

    public short nextShort(){
        if(scanner == null)
            scanner = IO.getSystemScanner();
        return scanner.nextShort();
    }

    public String nextLine() {
        if(scanner == null)
            scanner = IO.getSystemScanner();
        return scanner.nextLine();
    }
}
