package com.ex.controllers.io;

import java.io.PrintStream;

public class ConsoleOut {
    private ConsoleOut(){}

    public static final PrintStream err = IO.getConsoleErr();

    public static void print(String string){
        IO.getConsoleOut().print(string);
    }
    public static void println(String string){
        IO.getConsoleOut().println(string);
    }
    public static void printErr(String string){
        IO.getConsoleErr().println(string);
    }
}
