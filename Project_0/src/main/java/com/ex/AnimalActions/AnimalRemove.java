package com.ex.AnimalActions;

import com.ex.DAO.*;
import com.ex.Objects.Animals;
import com.ex.Objects.Keepers;
import com.ex.keepers.KeeperAccess;
import com.ex.main.*;

import java.util.List;
import java.util.Scanner;

/*Class Description
    *The AnimalRemove Screen connects with the Postgres Database to delete an Animal from the inventory
*/

public class AnimalRemove implements Screen {//Start of AnimalRemove Class

//Instant Variables
    private Scanner s = new Scanner(System.in);
    private String name, species,user;
    private Animals animal = new Animals();
    private Keepers trans = new Keepers();
    private GetEnvironmentVar getVar = new GetEnvironmentVar();

/*OLD FILE IO CODE REPLACED NOW UNUSED
*   private FileIoDAO fileIoDAO;
*    private int row,index=100;
*   private String[] animalName;*/

//Constructor
    public AnimalRemove(String user){
        this.user = user;
        trans.setUsernameKey(user);
    }

//Methods
    public Screen doScreen(Runner anInterface) {//Start of doScreen Method

        Runner connectionUtils = new PostgresConnectionUtil(getVar.getUrl(),getVar.getUsername(),getVar.getPassword(),getVar.getSchema());
        DAO<Animals> animalRepo = new SqlDatabaseAnimals(connectionUtils);
        DAO<Keepers> transaction = new SqlDatabaseKeepers(connectionUtils);

        List<Animals> allAnimals = animalRepo.findAll();//Invoking SqlDatabaseAnimals findAll method
            for(Animals a : allAnimals) {
                System.out.println( a.getAnimalName()+" "+ a.getAnimalType());//Outputting only Animal name and type return from findAll
            }
            //Getting Keeper's input and setting it to Animal Object
            System.out.println("\nEnter the name of animal to remove:");
                name=s.nextLine();
                    animal.setAnimalName(name);

            System.out.println("Enter the species of animal to remove:");
                species = s.nextLine();
                 animal.setAnimalType(species);

            animalRepo.delete(animal);//Invoking SqlDatabaseAnimals delete method

            trans.setAction("Removed " + animal.getAnimalName());
            transaction.save(trans);//Invoking SqlDatabaseKeepers save method
            return new KeeperAccess(user); //Return Keeper Only Menu
    }//End of doScreen Method


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
}//End of AnimalRemove Class

