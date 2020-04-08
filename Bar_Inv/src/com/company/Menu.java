package com.company;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class Menu {
    //view options
    public void run(){
        System.out.println("Choose an option, then press Enter:");
        System.out.println("1. Update inventory");
        System.out.println("2. Remove inventory");
        System.out.println("3. View low inventory items");
        System.out.println("Press 0 to exit");

        //listen for input
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();

        switch (choice){
            case 1:
                //update inventory
                break;
            case 2:
                //remove inventory item
                break;
            case 3:
                //view low inventory items
                break;
            case 0:
                //exit
                break;
            default:
                System.out.println("Invalid entry");
        }
    }

}
