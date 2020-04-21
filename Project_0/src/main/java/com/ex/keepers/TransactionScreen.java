package com.ex.keepers;

import com.ex.DAO.*;
import com.ex.main.Runner;
import com.ex.main.Screen;
import java.util.List;
/*Allows the keepers to view all the additions and removals from the zoo*/

public class TransactionScreen implements Screen {
//Instant Variables
    private GetEnvironmentVar getVar = new GetEnvironmentVar();
    private String user;

//Constructors
public TransactionScreen(String user){
    this.user=user;
}

//Methods
    public Screen doScreen(Runner anInterface) {
        Runner connectionUtils = new PostgresConnectionUtil(getVar.getUrl(), getVar.getUsername(), getVar.getPassword(), getVar.getSchema());
        DAO<Keepers> transRepo = new SqlDatabaseKeepers(connectionUtils);

        List<Keepers> trans = transRepo.specificFind();


        for(Keepers t: trans) {
            System.out.println(t.getFirstname()+ " " + t.getLastname()+"\n\t"+t.getAction()+" on "+t.getTime());
        }

        return new KeeperAccess(user);
    }
}
