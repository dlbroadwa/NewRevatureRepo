package com.ex.AnimalActions;

import com.ex.DAO.*;
import com.ex.Objects.Animals;
import com.ex.keepers.KeeperAccess;
import com.ex.main.Runner;
import com.ex.main.Screen;


import java.util.List;

/*
* InventoryScreen both KeeperAccess and GuestAccess can return to a new InventoryScreen
* Creates a Runner that is a new PostgresConnectionUtil to pass the need information to PostgresConnectionUtil to set the information
* in their respective strings to create a connection with the data base
* Uses the findAll method from the AnimalDAO and SqlDatabaseAnimals returns the animals
*/

public class InventoryScreen implements Screen {

//Instant Variables
    private Boolean isKeeper;
    private GetEnvironmentVar getVar = new GetEnvironmentVar();
    private String user;

//Constructors
    public InventoryScreen(Boolean isKeeper){
        this.isKeeper = isKeeper;
    }

    public InventoryScreen(Boolean isKeeper,String user){
        this.isKeeper = isKeeper;
        this.user = user;
    }

//Methods
    public Screen doScreen(Runner anInterface){
        Runner connectionUtils = new PostgresConnectionUtil(getVar.getUrl(), getVar.getUsername(), getVar.getPassword(), getVar.getSchema());
        DAO<Animals> animalRepo = new SqlDatabaseAnimals(connectionUtils);

        List<Animals> allAnimals = animalRepo.findAll();//Invoking SqlDatabaseAnimals findAll method

        for(Animals a : allAnimals) {//Outputting return from findAll method
            System.out.println( a.getAnimalName()+" the "+ a.getAnimalType()+" \n\tGender:"+ a.getSex()+" \n\tAge:"+ a.getAge()+ " \n\tEnclosure:"+ a.getEnclosure());
        }

        if(isKeeper)//Passed Boolean from Keeper Access to allow Keeper Menu to return until exited without Guests entering
        {
            return new KeeperAccess(user);
        }
        return null;
    }

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

}
