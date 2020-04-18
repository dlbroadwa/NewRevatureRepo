package com.ex.main;

import com.ex.DAO.DAO;
import com.ex.DAO.Animals;
import com.ex.DAO.GetEnvironmentVar;
import com.ex.DAO.SqlDatabaseAnimals;
import com.ex.keepers.KeeperAccess;


import java.util.List;

/*
* InventoryScreen both KeeperAccess and GuestAccess can return to a new InventoryScreen
* Creates a Runner that is a new PostgresConnectionUtil to pass the need information to PostgresConnectionUtil to set the information
* in their respective strings to create a connection with the data base
* Uses the findAll method from the AnimalDAO and SqlDatabaseAnimals returns the animals
*/

public class InventoryScreen implements Screen {
    private Boolean isKeeper;
    private GetEnvironmentVar getVar = new GetEnvironmentVar();
    private String username;

    public InventoryScreen(Boolean isKeeper){
        this.isKeeper = isKeeper;
    }

//Methods
    public Screen doScreen(Runner anInterface){
        Runner connectionUtils = new PostgresConnectionUtil(getVar.getUrl(), getVar.getUsername(), getVar.getPassword(), getVar.getSchema());
        DAO<Animals> animalRepo = new SqlDatabaseAnimals(connectionUtils);

        List<Animals> allAnimals = animalRepo.findAll();

        for(Animals a : allAnimals) {
            System.out.println( a.getAnimalName()+" the "+ a.getAnimalType()+" \n\tGender:"+ a.getSex()+" \n\tAge:"+ a.getAge()+ " \n\tEnclosure:"+ a.getEnclosure());
        }
        if(isKeeper)
        {
            return new KeeperAccess();
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
