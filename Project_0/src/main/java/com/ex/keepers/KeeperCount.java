package com.ex.keepers;

import com.ex.DAO.*;
import com.ex.Objects.Keepers;
import com.ex.main.Runner;
import com.ex.main.Screen;
import java.util.List;

/*Class Description:
    *Prints out the number of Keepers and their first and last name
*/

public class KeeperCount implements Screen {//Start of KeeperCount Class
//Instant Variables
    private GetEnvironmentVar getVar = new GetEnvironmentVar();
    private String user;

//Constructors
    public KeeperCount(String user){
        this.user = user;
    }

//Methods
    public Screen doScreen(Runner anInterface) {//Start of doScreen Method
        Runner connectionUtils = new PostgresConnectionUtil(getVar.getUrl(), getVar.getUsername(), getVar.getPassword(), getVar.getSchema());
        DAO<Keepers> keepers = new SqlDatabaseKeepers(connectionUtils);
        Integer num = new Integer(0);
        num = keepers.numberOf();//Invokes the SqlDatebaseKeepers numberOf method

        System.out.println("There are " + num + " keepers that keep Revature Zoo running!");
        List<Keepers> allKeepers = keepers.findAll();//Invoke the SqlDatabaseKeepers findAll method
            for(Keepers k: allKeepers){
                System.out.println(k.getFirstname()+" "+ k.getLastname());
            }
        return new KeeperAccess(user);
    }//End of doScreen Method
}//End of KeeperCount Class
