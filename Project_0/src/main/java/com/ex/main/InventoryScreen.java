package com.ex.main;

import com.ex.animal_dao.AnimalDAO;
import com.ex.animal_dao.Animals;
import com.ex.animal_dao.SqlDatabaseAnimals;
import com.ex.keepers.KeeperAccess;


import java.util.List;

/*
* InventoryScreen both KeeperAccess and GuestAccess can return to a new InventoryScreen
* Creates a Runner that is a new PostgresConnectionUtil to pass the need information to PostgresConnectionUtil to set the information
* in their respective strings to create a connection with the data base
* Uses the findAll method from the AnimalDAO and SqlDatabaseAnimals returns the animals
*/

public class InventoryScreen implements Screen {

//Methods
    public Screen doScreen(Runner anInterface){
        Runner connectionUtils = new PostgresConnectionUtil(
                "jdbc:postgresql://database-1.cb402pxtppo6.us-east-2.rds.amazonaws.com:5432/postgres",
                "paityn", "revature", "project_0");
        AnimalDAO<Animals,String, String , Integer, Integer> animalRepo = new SqlDatabaseAnimals(connectionUtils);

        List<Animals> allAnimals = animalRepo.findAll();

        for(Animals a : allAnimals) {
            System.out.println( a.getAnimalName()+" "+ a.getAnimalType()+" "+ a.getSex()+" "+ a.getAge()+ " Enclosure:"+ a.getEnclosure());
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
