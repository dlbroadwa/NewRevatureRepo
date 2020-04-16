package com.ex.keepers;

import com.ex.animal_dao.AnimalDAO;
import com.ex.animal_dao.Animals;
import com.ex.main.PostgresConnectionUtil;
import com.ex.animal_dao.SqlDatabaseAnimals;
import com.ex.main.*;
import java.util.Scanner;

/*The AnimalRemove Screen connects with the Postgres Database to delete an Animal from the inventory*/

public class AnimalRemove extends InventoryScreen implements Screen {

//Instant Variables
    private Scanner s = new Scanner(System.in);
    private String name, species, sex;
    private int age,enclosure;
    private Animals animal = new Animals();
    private String animalToRemove=null;

/*OLD FILE IO CODE REPLACED NOW UNUSED
*   private FileIoDAO fileIoDAO;
*    private int row,index=100;
*   private String[] animalName;*/

//Methods
    public Screen doScreen(Runner anInterface) {

        Runner connectionUtils = new PostgresConnectionUtil(
             "jdbc:postgresql://database-1.cb402pxtppo6.us-east-2.rds.amazonaws.com:5432/postgres",
             "paityn", "revature", "project_0");
        AnimalDAO<Animals, String, String , Integer, Integer> animalRepo = new SqlDatabaseAnimals(connectionUtils);

            System.out.println("Enter the animal name:");
                name=s.nextLine();
            System.out.println("Enter the animal species:");
                species = s.nextLine();

            animal.setAnimalName(name);
            animal.setAnimalType(species);
            animalRepo.delete(animal);

            return new KeeperAccess();
    }


//OLD  IO CODE REPLACED NOW UNUSED
 //   public AnimalRemove(){
//        FileIoDAO fileIoDAO = ((KeeperGuestSorter) anInterface).getFileIoDAO();
//
//        System.out.println("Enter name of animal to remove");
//            animalToRemove = s.nextLine();
//
//        for(row=0; row<100; row++) {
//            String animalInventory = fileIoDAO.getAnimalInventory(row);
//                animalName = animalInventory.split(" ");
//
//            if (animalInventory == null) {
//                break;
//            }
//            else if(animalName[0].toLowerCase().equals(animalToRemove.toLowerCase())){
//                index=row;
//                if(index!=100)
//                {
//                    System.out.println("Animal Found");
//                    break;
//                }
//            }
//            else
//            {
//                if(animalInventory == null) {
//                    System.out.println("Animal Not Found");
//                }
//             }
//    }
}

