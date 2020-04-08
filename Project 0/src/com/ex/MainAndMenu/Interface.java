package com.ex.MainAndMenu;
import com.ex.Guests.GuestAccess;
import com.ex.Keepers.KeeperAccess;

import java.util.Scanner;

public class Interface extends Runner {
    Scanner s = new Scanner(System.in);
    int menuOp;
    Runner keeper = new KeeperAccess();
    Runner guest = new GuestAccess();
    @Override
    public void run() {
        do {
            System.out.println("Keepers enter 1:\nGuests enter 2:");
            menuOp = s.nextInt();
            //       Switch Works but accepts non-integer values and crashes (CLEAN UP LATER)
            switch (menuOp) {
                case 1:
                    keeper.run();
                    break;
                case 2:
                    guest.run();
                    break;
                default:
                    System.out.println("Incorrect Input");
                    break;
            }
        }while (menuOp!= 1 && menuOp!=2);

    }
}
