package com.ex.keepers;

import com.ex.DAO.Animals;
import com.ex.DAO.DAO;
import com.ex.DAO.SqlDatabaseAnimals;
import com.ex.DAO.GetEnvironmentVar;
import com.ex.main.PostgresConnectionUtil;
import com.ex.main.Runner;
import com.ex.main.Screen;

import java.util.List;
import java.util.Scanner;
//import com.ex.dao.FileIoDAO;
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.lang.reflect.Array;
//import java.util.List;

/*The Animal Add Screen connects with the Postgres Database to insert a new Animal in to the inventory*/

public class AnimalAdd implements Screen {

//Instant Variables
    private Scanner s = new Scanner(System.in);
    private Animals animal = new Animals();
    private GetEnvironmentVar getVar = new GetEnvironmentVar();

//Methods
    public Screen doScreen(Runner anInterface) {

        Runner connectionUtils = new PostgresConnectionUtil(getVar.getUrl(),getVar.getUsername(),getVar.getPassword(),getVar.getSchema());
        DAO<Animals> animalRepo = new SqlDatabaseAnimals(connectionUtils);

        List<Animals> allAnimals = animalRepo.specificFind();
            for(Animals a : allAnimals) {
            System.out.println("Enclosure:"+ a.getEnclosure()+" "+a.getAnimalType());
        }

        System.out.println("\nEnter the animal name:");
                animal.setAnimalName(s.nextLine());

        System.out.println("Enter the animal species:");
                animal.setAnimalType(s.nextLine());

        System.out.println("Enter the animal sex(F or M):");
                animal.setSex(s.nextLine());

        System.out.println("Enter the animal age:");
                animal.setAge(s.nextInt());

        System.out.println("Enter the animal's Enclosure number:");
                animal.setEnclosure(s.nextInt());

        animalRepo.save(animal);

        return new KeeperAccess();
    }

//OLD  IO CODE REPLACED NOW UNUSED
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
