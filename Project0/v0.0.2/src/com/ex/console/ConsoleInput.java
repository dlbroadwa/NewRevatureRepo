package com.ex.console;

import java.io.Console;

public class ConsoleInput {
    private static java.util.Scanner scanner;

    public static void initialize(){
        if(scanner == null){
            scanner = new java.util.Scanner(System.in);
        }
    }

    public static double getNumber(){
        boolean inputNeeded = true;
        double num = 0;
        System.out.println("Please enter a number.");
        do{
            try{
                num = scanner.nextDouble();
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

    private ConsoleInput(){
    }
}
