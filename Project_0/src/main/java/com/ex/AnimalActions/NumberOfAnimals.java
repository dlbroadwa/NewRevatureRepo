package com.ex.AnimalActions;

import com.ex.DAO.DAO;
import com.ex.DAO.GetEnvironmentVar;
import com.ex.DAO.PostgresConnectionUtil;
import com.ex.DAO.SqlDatabaseAnimals;
import com.ex.Objects.Animals;
import com.ex.main.Runner;
import com.ex.main.Screen;

/*Class Description
     *Prints out the number of Animals and then changes to Inventory Screen
 */

public class NumberOfAnimals implements Screen {//Start of NumberOfAnimals Class
//Instant Variables
    private GetEnvironmentVar getVar = new GetEnvironmentVar();
    private String user;
    private Boolean isKeeper;

//Constructors
    public NumberOfAnimals(String user,Boolean isKeeper){
        this.user = user;
        this.isKeeper = isKeeper;
    }

    public NumberOfAnimals(Boolean isKeeper){
        this.isKeeper = isKeeper;
    }

//Methods
    public Screen doScreen(Runner anInterface) {//Start of doScreen Method
        Runner connectionUtils = new PostgresConnectionUtil(getVar.getUrl(), getVar.getUsername(), getVar.getPassword(), getVar.getSchema());
        DAO<Animals> animalRepo = new SqlDatabaseAnimals(connectionUtils);
        Integer num = new Integer(0);
                num = animalRepo.numberOf();//Invokes SqlDatebaseAnimals numberOf method

        System.out.println("There are " + num + " animals that call Revature Zoo home!");
        return new InventoryScreen(user, isKeeper);
    }//End of doScreen Method
}//End of NumberOfAnimals Class
