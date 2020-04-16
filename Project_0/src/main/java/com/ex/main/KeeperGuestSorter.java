package com.ex.main;

import com.ex.guests.GuestAccess;
import com.ex.keepers.KeeperScreeningScreen;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.ex.dao.keeper_dao.FileIoDAO;

public class KeeperGuestSorter extends Runner {
    private FileIoDAO fileIoDAO = null;
   private FileIoDAO passwordKeeper = null;

    private Screen keeper= null;
    private Screen guest = null;
    private Scanner s = new Scanner(System.in);
    private int menuOp;


    public KeeperGuestSorter(){
        keeper = new KeeperScreeningScreen();
        guest = new GuestAccess();
         fileIoDAO = new FileIoDAO("resource/animalInventory.txt");
        passwordKeeper = new FileIoDAO("resource/usernamesAndPasswordsKeeper");
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

    public Connection getConnection() throws SQLException {
        return null;
    }



    public FileIoDAO getFileIoDAO() {
        return fileIoDAO;
    }

    public FileIoDAO getPasswordKeeper(int i){
        return passwordKeeper;
    }
//    public KeeperGuestSorter(String[] animals) {
//        this();
//        this.animals= animals;
//    }

}
