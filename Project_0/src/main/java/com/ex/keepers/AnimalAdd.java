package com.ex.keepers;

import com.ex.animal_dao.Animals;
import com.ex.animal_dao.AnimalDAO;
import com.ex.animal_dao.SqlDatabaseAnimals;
import com.ex.main.PostgresConnectionUtil;
import com.ex.main.Runner;
import com.ex.main.Screen;
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
    private String name, species, sex;
    private int age,enclosure;
    private Animals animal = new Animals();

//Methods
    public Screen doScreen(Runner anInterface) {

        Runner connectionUtils = new PostgresConnectionUtil(
                "jdbc:postgresql://database-1.cb402pxtppo6.us-east-2.rds.amazonaws.com:5432/postgres",
                "paityn", "revature", "project_0");
        AnimalDAO<Animals, String, String , Integer, Integer> animalRepo = new SqlDatabaseAnimals(connectionUtils);

        System.out.println("Enter the animal name:");
            name=s.nextLine();
                animal.setAnimalName(name);

        System.out.println("Enter the animal species:");
            species = s.nextLine();
                animal.setAnimalType(species);

       while (sex != "F"|| sex!="M") {
           System.out.println("Enter the animal sex(F or M):");
                sex = s.nextLine();}
                    animal.setSex(sex);

        System.out.println("Enter the animal age:");
            age = s.nextInt();
                animal.setAge(age);

        System.out.println("Enter the animal's Enclosure number:");
            enclosure = s.nextInt();
                animal.setEnclosure(enclosure);

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
