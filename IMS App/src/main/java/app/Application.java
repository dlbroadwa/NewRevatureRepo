package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class Application
{
    private Scanner scanner = new Scanner(System.in);
    private Scanner fileScanner;
    public Scanner getScanner()
    {
        return this.scanner;
    }
    public Scanner getScanner(File file) throws FileNotFoundException
    {
        this.fileScanner = new Scanner(file);
        return this.fileScanner;
    }
}
