package com.ex.keepers;

import com.ex.DAO.*;
import com.ex.Objects.Keepers;
import com.ex.main.Runner;
import com.ex.main.Screen;
import java.util.List;

/*Class Descriptions
    *Allows the keepers to view all the additions and removals from the zoo
*/

public class TransactionScreen implements Screen {//Start of TransactionScreen Class
//Instant Variables
    private GetEnvironmentVar getVar = new GetEnvironmentVar();
    private String user;

//Constructors
public TransactionScreen(String user){
    this.user=user;
}

//Methods
    public Screen doScreen(Runner anInterface) {//Start of doScreen Method
        Runner connectionUtils = new PostgresConnectionUtil(getVar.getUrl(), getVar.getUsername(), getVar.getPassword(), getVar.getSchema());
        DAO<Keepers> transRepo = new SqlDatabaseKeepers(connectionUtils);

        List<Keepers> trans = transRepo.specificFind();//Invoke SqlDatabaseKeepers specificFind method


        for(Keepers t: trans) {//Outputting return from specificFind method
            System.out.println(t.getFirstname()+ " " + t.getLastname()+"\n\t"+t.getAction()+" on "+t.getTime());
        }

        return new KeeperAccess(user);//Return to Keeper Only Menu
    }//End of doScreen Method
}//End of TransactionScreen Class
