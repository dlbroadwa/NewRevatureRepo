package com.ex.keepers;

import com.ex.DAO.*;
import com.ex.main.Runner;
import com.ex.main.Screen;

import java.util.List;

public class TransactionScreen implements Screen {
//Instant Variables
    private GetEnvironmentVar getVar = new GetEnvironmentVar();

//Methods
    public Screen doScreen(Runner anInterface) {
        Runner connectionUtils = new PostgresConnectionUtil(getVar.getUrl(), getVar.getUsername(), getVar.getPassword(), getVar.getSchema());
        DAO<Keepers> transRepo = new SqlDatabaseKeepers(connectionUtils);

        List<Keepers> trans = transRepo.specificFind();


        for(Keepers t: trans) {
            System.out.println(t.getFirstname()+ " " + t.getLastname()+"\n\t"+t.getAction()+" on "+t.getTime());
        }

        return new KeeperAccess();
    }
}
