package com.ex.io;

import java.util.Scanner;

/**
 * Class that handles obtaining input from System.in.
 */
public class ConsoleInputSource implements InputSource {
    Scanner scanner;

    public ConsoleInputSource() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String getInput() {
        return scanner.nextLine();
    }
}
