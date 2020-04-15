package com.ex.keepers;

import com.ex.dao.FileIoDAO;
import com.ex.main.InventoryScreen;
import com.ex.main.KeeperGuestSorter;
import com.ex.main.Runner;
import com.ex.main.Screen;

import java.io.*;
import java.util.Scanner;

public class AnimalRemove extends InventoryScreen implements Screen {

    private String animalToRemove=null;
    private String[] animalName;
    private Scanner s = new Scanner(System.in);
    private FileIoDAO fileIoDAO;
    private int row,index=100;

    public Screen doScreen(Runner anInterface) {

        FileIoDAO fileIoDAO = ((KeeperGuestSorter) anInterface).getFileIoDAO();

        System.out.println("Enter name of animal to remove");
            animalToRemove = s.nextLine();

        for(row=0; row<100; row++) {
            String animalInventory = fileIoDAO.getAnimalInventory(row);
                animalName = animalInventory.split(" ");

            if (animalInventory == null) {
                break;
            }
            else if(animalName[0].toLowerCase().equals(animalToRemove.toLowerCase())){
                index=row;
                if(index!=100)
                {
                    System.out.println("Animal Found");
                    break;
                }
            }
            else
            {
                if(animalInventory == null) {
                    System.out.println("Animal Not Found");
                }
             }
        }

        return null;

    }

    public AnimalRemove(){

    }
}

