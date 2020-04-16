package com.ex.main;

import com.ex.dao.animal_dao.AnimalDAO;
import com.ex.dao.animal_dao.Animals;
import com.ex.dao.PostgresConnectionUtil;
import com.ex.dao.animal_dao.SqlDatabaseAnimals;


import java.util.List;

public class InventoryScreen implements Screen {

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
