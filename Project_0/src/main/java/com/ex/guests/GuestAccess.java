package com.ex.guests;

import com.ex.AnimalActions.*;
import com.ex.main.Runner;
import com.ex.main.Screen;

import java.util.InputMismatchException;
import java.util.Scanner;

/*Class Description:
     * This is where non-Keepers AKA Guests get sent from the KeeperGuestSorter. This Screen prints a greeting and returns InventoryScreen
 */
public class GuestAccess implements Screen {//Start of GuestAccess Class
//Instant Variables
    boolean gotInt;
    int choice;
    Scanner s = new Scanner(System.in);

//Constructors
    public GuestAccess(){

    }

//Methods
    public Screen doScreen(Runner anInterface) {//Start of doScreen Method

        while(!gotInt) {
            try {
                System.out.println("\tView All Animals(1)\n\tAnimal Types and Where to Find Them(2)\n\tExit(3)");
                choice = s.nextInt();
                gotInt=true;
                switch (choice) {
                    case 1:
                        return new NumberOfAnimals(false);

                    case 2:
                        return new SpeciesViewing(false);

                     case 3:
                        return null;//Only input to exit the program

                    default:
                        System.out.println("Invalid Input.");
                        gotInt = false;
                        return new GuestAccess();

                }
            } catch (InputMismatchException e) {
                System.out.println("Not a number");
                s.next();
            }
        }
        return null;
    }//End of doScreen Method

}//End of GuestAccess Class
