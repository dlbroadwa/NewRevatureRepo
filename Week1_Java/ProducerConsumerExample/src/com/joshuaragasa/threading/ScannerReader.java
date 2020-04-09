package com.joshuaragasa.threading;

import java.util.Scanner;

public class ScannerReader
{
    private Scanner scanner;

    public Scanner getScanner() {
        if (this.scanner == null) {
            this.scanner = new Scanner(System.in);
            return this.scanner;
        } else {
            return this.scanner;
        }
    }
}
