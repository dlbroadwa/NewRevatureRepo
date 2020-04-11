package app;

import java.util.Scanner;

public class LibraryApp implements Application {
    private Scanner scanner;

    LibraryApp() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void run() {

    }

    public Scanner getScanner() {
        return scanner;
    }
}
