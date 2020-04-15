package com.ex.main;
import com.ex.dao.FileIoDAO;
import com.ex.guests.GuestAccess;
import com.ex.keepers.KeeperScreeningScreen;


import java.util.InputMismatchException;
import java.util.Scanner;

public class KeeperGuestSorter extends Runner {

    private Screen keeper= null;
    private Screen guest = null;
    private Scanner s = new Scanner(System.in);
    private FileIoDAO fileIoDAO = null;
    private FileIoDAO passwordKeeper = null;
    private int menuOp;

    public KeeperGuestSorter(){
        keeper = new KeeperScreeningScreen();
        guest = new GuestAccess();
        fileIoDAO = new FileIoDAO("resource/animalInventory.txt");
        passwordKeeper = new FileIoDAO("resource/usernamesAndPasswordsKeeper");
    }

    public KeeperGuestSorter(String[] animals) {
        this();
        this.animals= animals;
    }

    public void run() {

        boolean gotInt = false;
        while(!gotInt) {
            try {
                System.out.println("Keepers enter 1:\nGuests enter 2:");
                menuOp = s.nextInt();
                gotInt = true;
                switch (menuOp) {
                    case 1:
                        while (keeper != null) {
                            keeper = keeper.doScreen(this);
                        }
                        break;
                    case 2:
                        while (guest != null) {
                            guest = guest.doScreen(this);
                        }
                        break;
                    default:
                        System.out.println("Incorrect Input");
                        gotInt=false;
                        break;
                }
             } catch (InputMismatchException e) {
                 System.out.println("Not a number");
                 s.next();
                }
      }
    }

    public FileIoDAO getFileIoDAO() {
        return fileIoDAO;
    }

    public FileIoDAO getPasswordKeeper(int i){
        return passwordKeeper;
    }

}
