package com.ex.keepers;
import com.ex.AnimalActions.*;
import com.ex.main.*;

import java.util.InputMismatchException;
import java.util.Scanner;

/*Class Description:
    *KeeperAccess class is intended to be the keeper only menu which will include animal info and add/remove animals
*/

public class KeeperAccess implements Screen {//Start of KeeperAccess Class

//Instant Variables
    private Scanner s = new Scanner(System.in);
    private int choice;
    private Boolean gotInt = false;
    private String user;

/*
    *Must pass user string between all classes so transactions can be logged with the correct Keeper
    *no matter how many they make before they exit the program
*/

//Constructors
    public KeeperAccess(){}

    public KeeperAccess(String user){
        this.user = user;
    }

//Methods
    public Screen doScreen(Runner anInterface) {//Start of doScreen Method
        while(!gotInt) {
            try {
                System.out.println("Keeper Only Menu\n\tView Animals(1)\n\tSpecies(2)\n\tAdd An Animal(3)\n\tRemove An Animal(4)" +
                        "\n\tTrack Animal Changes(5)\n\tKeepers(6)\n\tExit(7)");
                    choice = s.nextInt();
                gotInt=true;
                switch (choice) {
                    case 1:
                        return new NumberOfAnimals(user,true);

                    case 2:
                        return new SpeciesViewing(user, true);

                    case 3:
                        return new AnimalAdd(user);

                    case 4:
                        return new AnimalRemove(user);

                    case 5:
                        return new TransactionScreen(user);

                    case 6:
                        return new KeeperCount(user);

                    case 7:
                        return null;//Only input to exit the program

                    default:
                        System.out.println("Invalid Input.");
                        gotInt = false;
                        return new KeeperAccess();

                }
            } catch (InputMismatchException e) {
                System.out.println("Not a number");
                    s.next();
            }
        }
        return null;
    }//End of doScreen Method

}//End of KeeperAccess Class
