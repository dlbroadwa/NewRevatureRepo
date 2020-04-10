package com.ex.MainAndMenu;
import com.ex.DAO.FileIoDAO;
import com.ex.Guests.GuestAccess;
import com.ex.Keepers.KeeperAccess;

import java.io.IOException;
import java.util.Scanner;

public class KeeperGuestSorter extends Runner {

    private Screen keeper= null;
    private Screen guest = null;
    private Scanner s = new Scanner(System.in);
    private Screen animalInv;
    private int menuOp;

    public KeeperGuestSorter(){
        keeper = new KeeperAccess();
        guest = new GuestAccess();
        //animalInv = new FileIoDAO("resources/animalInventory");
    }

    public KeeperGuestSorter(String[][] animals) throws IOException {
        // call the no-args constructor to setup without repeating code
        this();
        this.animals= animals;
    }

    public void run() {
        do{
            System.out.println("Keepers enter 1:\nGuests enter 2:");
            menuOp = s.nextInt();

            switch (menuOp) {
                case 1:
                    while(keeper != null) {
                        keeper = keeper.doScreen(this);
                    }
                    break;
                case 2:
                    while(guest !=null) {
                        guest = guest.doScreen(this);
                    }
                    break;
                default:
                    System.out.println("Incorrect Input");
                    break;
            }
        }while(menuOp!= 1 && menuOp!=2);
    }

    public Screen getKeeper() {
        return keeper;
    }

    public void setKeeper(Screen keeper) {
        this.keeper = keeper;
    }

    public Screen getGuest() {
        return guest;
    }

    public void setGuest(Screen guest) {
        this.guest = guest;
    }
}
