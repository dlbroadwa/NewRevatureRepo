package com.ex.keepers;
import com.ex.main.*;

import java.util.InputMismatchException;
import java.util.Scanner;

/*KeeperAccess class is intended to be the keeper only menu which will include animal info and add/remove animals*/

public class KeeperAccess implements Screen {

    private Scanner s = new Scanner(System.in);
    private int choice;
    private Boolean gotInt = false;

    public Screen doScreen(Runner anInterface) {

        while(!gotInt) {

            try {

                System.out.println("View Animals(1)\nAdd An Animal(2)\nRemove An Animal(3)\nExit(4)");
                    choice = s.nextInt();
                gotInt=true;

                switch (choice) {
                    case 1:
                        return new InventoryScreen();

                    case 2:
                        return new AnimalAdd();

                    case 3:
                        return new AnimalRemove();

                    case 4:
                        return null;

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
