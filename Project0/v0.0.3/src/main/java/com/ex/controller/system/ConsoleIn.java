package com.ex.controller.system;

import java.util.Scanner;

public class ConsoleIn {
    private ConsoleIn(){}

    private static Scanner scanner;

    public static String next(){
        if(scanner == null)
            scanner = IO.getSystemScanner();
        return scanner.next();
    }
    public static int nextInt(){
        if(scanner == null)
            scanner = IO.getSystemScanner();
        return scanner.nextInt();
    }
    public static double nextDouble(){
        if(scanner == null)
            scanner = IO.getSystemScanner();
        return scanner.nextDouble();
    }
    public static short nextShort(){
        if(scanner == null)
            scanner = IO.getSystemScanner();
        return scanner.nextShort();
    }
    public static String nextLine() {
        if(scanner == null)
            scanner = IO.getSystemScanner();
        return scanner.nextLine();
    }
}
