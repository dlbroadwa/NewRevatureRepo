package com.ex.keepers;
import com.ex.AnimalActions.AnimalAdd;
import com.ex.AnimalActions.AnimalRemove;
import com.ex.AnimalActions.InventoryScreen;
import com.ex.main.*;

import java.util.InputMismatchException;
import java.util.Scanner;

/*KeeperAccess class is intended to be the keeper only menu which will include animal info and add/remove animals*/

public class KeeperAccess implements Screen {

    //Instant Variables
    private Scanner s = new Scanner(System.in);
    private int choice;
    private Boolean gotInt = false;
    private String user;/*Must pass user string between all classes so transactions can be logged with the correct Keeper
                                    no matter how many they make before they exit the program*/

//Constructors
    public KeeperAccess(){}

    public KeeperAccess(String user){
        this.user = user;
    }

 //Methods
    public Screen doScreen(Runner anInterface) {
        while(!gotInt) {
            try {
                System.out.println("Keeper Only Menu\n\tView Animals(1)\n\tAdd An Animal(2)\n\tRemove An Animal(3)\n\tView All Previous Transactions(4)" +
                        "\n\tExit(5)");
                    choice = s.nextInt();
                gotInt=true;
                switch (choice) {
                    case 1:
                        return new InventoryScreen(true,user);

                    case 2:
                        return new AnimalAdd(user);

                    case 3:
                        return new AnimalRemove(user);

                    case 4:
                        return new TransactionScreen(user);

                    case 5:
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
    }

}
