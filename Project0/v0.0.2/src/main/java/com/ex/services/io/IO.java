package com.ex.services.io;

import java.io.PrintStream;
import java.util.Scanner;

class IO {
    private IO(){
    }

    private static Scanner systemScanner;

    static Scanner getSystemScanner(){
        if(systemScanner == null)
            systemScanner = new Scanner(System.in);
        return systemScanner;
    }
    static PrintStream getConsoleOut(){
        return System.out;
    }
    static PrintStream getConsoleErr(){
        return System.err;
    }
}