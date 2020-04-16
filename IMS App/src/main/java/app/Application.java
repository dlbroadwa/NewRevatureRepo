package app;

import database.Database;

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
    public void exitSystem()
    {
        try
        {
            System.out.println("Exiting the Instrument Management System.....");
            Thread.sleep(2000);
            System.out.println("You have exited IMS. Come back soon!");
            System.exit(0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }
    }

}
