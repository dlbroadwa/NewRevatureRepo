package com.ex.MainAndMenu;
import com.ex.Guests.GuestAccess;
import com.ex.Keepers.KeeperAccess;

import java.util.Scanner;

public class Interface extends Runner {
    private Scanner s = new Scanner(System.in);
    private int menuOp;
    private Runner keeper;
    private Runner guest;

    public Interface() {
        keeper = new KeeperAccess();
        guest = new GuestAccess();
    }
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
