package com.ex.console;

import java.util.Scanner;

public class ConsoleInput {
    private ConsoleInput(){
        scanner = new Scanner(System.in);
    }

    private static java.util.Scanner scanner;
    private static ConsoleInput consoleInput;

    public static ConsoleInput getInstance(){
        if(consoleInput == null){
            consoleInput = new ConsoleInput();
        }
        return consoleInput;
    }
    public static String getName(){
        boolean inputNeeded = true;
        String name = null;
        do{
            try{
                System.out.print("Name: ");
                name = scanner.next();
                System.out.println("You entered: " + name + ".");
                return name;
            } catch (java.util.NoSuchElementException e) {
                System.out.println("Try again.");
            } catch (Exception e){
                e.printStackTrace();
            }
            scanner.nextLine();
        } while(inputNeeded);
        return name;
    }
    public static int getID(){
        boolean inputNeeded = true;
        int num = 0;
        do{
            try{
                System.out.println("ID number: ");
                num = scanner.nextInt();
                System.out.println("You entered " + num + ".");
                return num;
            } catch (java.util.InputMismatchException e) {
                System.out.println("That wasn't a number. Please enter just a number.");
            } catch (Exception e){
                e.printStackTrace();
            }
            scanner.nextLine();
        } while(inputNeeded);
        return num;
    }
}
