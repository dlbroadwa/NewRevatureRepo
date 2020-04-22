package com.ex.AnimalActions;

import com.ex.DAO.DAO;
import com.ex.DAO.GetEnvironmentVar;
import com.ex.DAO.PostgresConnectionUtil;
import com.ex.DAO.SqlDatabaseAnimals;
import com.ex.Objects.Animals;
import com.ex.guests.GuestAccess;
import com.ex.keepers.KeeperAccess;
import com.ex.main.Runner;
import com.ex.main.Screen;
import java.util.List;

/*Class Description
     *Prints out the number of Species and what the species are and their enclosures
 */

public class SpeciesViewing implements Screen {//Start of SpeciesViewing Class
//Instant Variables
    private GetEnvironmentVar getVar = new GetEnvironmentVar();
    private String user;
    private Boolean isKeeper;
    private Integer num;

//Constructors
    public SpeciesViewing(String user, Boolean isKeeper){
        this.user = user;
        this.isKeeper = isKeeper;
    }

    public SpeciesViewing(Boolean isKeeper){
        this.isKeeper = isKeeper;
    }

//Methods
    public Screen doScreen(Runner anInterface) {//Start of doScreen Method
        Runner connectionUtils = new PostgresConnectionUtil(getVar.getUrl(), getVar.getUsername(), getVar.getPassword(), getVar.getSchema());
            DAO<Animals> animalRepo = new SqlDatabaseAnimals(connectionUtils);

            num = animalRepo.specificNumberOf();//Invokes SqlDatebaseAnimals specificNumberOf method
        System.out.println("There are " + num + " different species of animals that call Revature Zoo home!");

        List<Animals> allAnimals = animalRepo.specificFind();//Invoking SqlDatabaseAnimals specificFind method
            for(Animals a : allAnimals) {
                System.out.println("Enclosure:"+ a.getEnclosure()+" "+a.getAnimalType());//Outputting the return from specificFind
            }

            if(isKeeper) {
                 return new KeeperAccess(user);
            }else {
                return new GuestAccess();
            }
    }//End of doScreen Method
}//End of SpeciesViewing Class
