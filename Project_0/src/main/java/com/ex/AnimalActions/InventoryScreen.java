package com.ex.AnimalActions;

import com.ex.DAO.*;
import com.ex.Objects.Animals;
import com.ex.guests.GuestAccess;
import com.ex.keepers.KeeperAccess;
import com.ex.main.Runner;
import com.ex.main.Screen;


import java.util.List;

/*Class Description
    * InventoryScreen both KeeperAccess and GuestAccess can return to a new InventoryScreen
    * Creates a Runner that is a new PostgresConnectionUtil to pass the need information to PostgresConnectionUtil to set the information
    * in their respective strings to create a connection with the data base
    * Uses the findAll method from the AnimalDAO and SqlDatabaseAnimals returns the animals
*/

public class InventoryScreen implements Screen {//Start of InventoryScreen Class

//Instant Variables
    private GetEnvironmentVar getVar = new GetEnvironmentVar();
    private String user;
    private Boolean isKeeper;

//Constructors
    public InventoryScreen(Boolean isKeeper){
        this.isKeeper = isKeeper;
    }

    public InventoryScreen(String user,Boolean isKeeper){
        this.user = user;
        this.isKeeper = isKeeper;
    }

//Methods
    public Screen doScreen(Runner anInterface){//Start of doScreen Method
        Runner connectionUtils = new PostgresConnectionUtil(getVar.getUrl(), getVar.getUsername(), getVar.getPassword(), getVar.getSchema());
        DAO<Animals> animalRepo = new SqlDatabaseAnimals(connectionUtils);

        List<Animals> allAnimals = animalRepo.findAll();//Invoking SqlDatabaseAnimals findAll method
        if(isKeeper) {
            for(Animals a : allAnimals) {//Outputting return from findAll method
                System.out.println( a.getAnimalName()+" the "+ a.getAnimalType()+"\tGender:"+ a.getSex()+"\tAge:"+ a.getAge()+ "\tEnclosure:"+ a.getEnclosure());
            }
            return new KeeperAccess(user);
        }
        else {
            for(Animals a : allAnimals) {//Outputting return from findAll method
                System.out.println( a.getAnimalName()+" the "+ a.getAnimalType()+"\tGender:"+ a.getSex()+"\tAge:"+ a.getAge());
            }
            return new GuestAccess();
        }
    }//End of doScreen Method

//OLD FILE IO CODE REPLACED NOW UNUSED
//    int row = 0;
//    private FileIoDAO fileIoDAO;
//
//    public Screen doScreen(Runner anInterface) {
//
//        FileIoDAO fileIoDAO = ((KeeperGuestSorter) anInterface).getFileIoDAO();
//
//        for (row = 0; row < 100; row++) {
//
//            String animalInventory = fileIoDAO.getAnimalInventory(row);
//
//            if (animalInventory == null) {
//                break;
//            } else {
//                System.out.println(animalInventory);
//            }
//    }
//
//        return null;
//    }
}//End of InventoryScreen Class
