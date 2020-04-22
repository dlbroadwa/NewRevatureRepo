package com.ex.main;

public class Main {
    /*
    *Project_0:
    * Created By:Paityn Maynard
    *
    * Project Description:
    *   This is the Revature Zoo Program Where Guests can view animals that are at the zoo
    *   And Keepers can add and remove animals on top of viewing them. Also Keepers are able to view the
    *   additions and removals made by other keepers and when they occured.
    *
    * The Main creates a Runner as a KeeperGuestSorter to direct the program to the KeeperGuestSorter class
    */
    public static void main(String[] args) {
       Runner anInterface = new KeeperGuestSorter();
        anInterface.run();

    }
}
