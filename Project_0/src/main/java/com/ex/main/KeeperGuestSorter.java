package com.ex.main;

import com.ex.guests.GuestAccess;
import com.ex.keepers.KeeperScreeningScreen;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
/*
* KeeperGuestSorter class first class implemented to display the menu choice between keeper and guest
* Keepers then move to the KeeperScreenScreen
* Guests move to the GuestAccessScreen
*/

public class KeeperGuestSorter extends Runner {

//OLD FILE IO CODE REPLACED NOW UNUSED
   // private FileIoDAO fileIoDAO = null;
   //private FileIoDAO passwordKeeper = null;

//Instant Variables
    private Screen keeper= null;
    private Screen guest = null;
    private Scanner s = new Scanner(System.in);
    private int menuOp;

//Constructor
    public KeeperGuestSorter(){
        keeper = new KeeperScreeningScreen();
        guest = new GuestAccess();

//OLD FILE IO CODE REPLACED NOW UNUSED
         //fileIoDAO = new FileIoDAO("resource/animalInventory.txt");//Initially used Files
        //passwordKeeper = new FileIoDAO("resource/usernamesAndPasswordsKeeper");
    }

//Methods
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

//Getters
    public Connection getConnection() throws SQLException {
        return null;
    }


//OLD FILE IO CODE REPLACED NOW UNUSED
//    public FileIoDAO getFileIoDAO() {
//        return fileIoDAO;
//    }
//
//    public FileIoDAO getPasswordKeeper(int i){
//        return passwordKeeper;
//    }
//    public KeeperGuestSorter(String[] animals) {
//        this();
//        this.animals= animals;
//    }

}
