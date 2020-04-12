package com.ex.services.io;

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
    public static String nextLine() {
        if(scanner == null)
            scanner = IO.getSystemScanner();
        return scanner.nextLine();
    }
}
