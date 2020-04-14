package com.ex.keepers;
import com.ex.main.*;

import java.util.Scanner;

/*KeeperAccess class is intended to be the keeper only menu which will include animal info and add/remove animals*/

public class KeeperAccess implements Screen {
    private Scanner s = new Scanner(System.in);
    private int choice;
    //private AnimalAdd animalAdd = null;
    public Screen doScreen(Runner anInterface) {
            System.out.println("View Animals(1)\nAdd An Animal(2)\nRemove An Animal(3)\nExit(4)");
            choice = s.nextInt();
            switch (choice) {
                case 1:
                    return new InventoryScreen();
                case 2:
                     return new AnimalAdd("resource/animalInventory.txt");
                case 3:
                    return new AnimalRemove();
                case 4:
                    return null;
                default:
                    return new KeeperAccess();
            }

    }

}
