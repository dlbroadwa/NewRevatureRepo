package com.ex.keepers;

import com.ex.dao.Animals;
import com.ex.dao.DAO;
import com.ex.dao.FileIoDAO;
import com.ex.dao.SqlDatabase;
import com.ex.main.PostgresConnectionUtil;
import com.ex.main.Runner;
import com.ex.main.Screen;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Scanner;

public class AnimalAdd implements Screen {
    private Scanner s = new Scanner(System.in);
    private String name, species, sex;
    private int age,enclosure;
    private Animals animal;
    @Override
    public Screen doScreen(Runner anInterface) {
        Runner connectionUtils = new PostgresConnectionUtil(
                "jdbc:postgresql://database-1.cb402pxtppo6.us-east-2.rds.amazonaws.com:5432/postgres",
                "paityn", "revature", "project_0");
        DAO<Animals, String, String , Integer, Integer> animalRepo = new SqlDatabase(connectionUtils);

        System.out.println("Enter the animal name:");
            name=s.nextLine();
        System.out.println("Enter the animal species:");
            species = s.nextLine();
        System.out.println("Enter the animal sex:");
             sex= s.nextLine();
        System.out.println("Enter the animal age:");
            age = s.nextInt();
        System.out.println("Enter the animal's Enclosure number:");
            enclosure = s.nextInt();

        return null;
    }


    //    public AnimalAdd(String animalFilePath){
//        System.out.println("Enter the animal you wish to add in this order---> Name Species Sex Age Location(Enclose#)");
//        animalToAdd = s.nextLine();
//

//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(animalFilePath, true));
//            writer.newLine();   //Add new line
//            writer.write(animalToAdd);
//            writer.close();
//            System.out.println(animalToAdd + "\nHas been added");
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        } ;
    // }
}
