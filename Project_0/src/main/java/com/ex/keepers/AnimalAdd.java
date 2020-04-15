package com.ex.keepers;

import com.ex.dao.FileIoDAO;
import com.ex.main.InventoryScreen;
import com.ex.main.Runner;
import com.ex.main.Screen;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AnimalAdd extends InventoryScreen implements Screen {
    private String animalToAdd;
    private Scanner s = new Scanner(System.in);


    public AnimalAdd(String animalFilePath){
        System.out.println("Enter the animal you wish to add in this order Name Species Sex Age Location(Enclose#)");
        animalToAdd = s.nextLine();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(animalFilePath, true));
            writer.newLine();   //Add new line
            writer.write(animalToAdd);
            writer.close();
            System.out.println(animalToAdd + "\nHas been added");
        }
        catch (IOException e) {
            e.printStackTrace();
        } ;

    }

    @Override
    public Screen doScreen(Runner anInterface) {
        //System.out.println("In the animal add screen");
        return null;
       // return new KeeperScreeningScreen();
    }
}
