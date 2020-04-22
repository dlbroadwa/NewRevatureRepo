package com.ex.keepers;

import com.ex.DAO.DAO;
import com.ex.DAO.GetEnvironmentVar;
import com.ex.DAO.PostgresConnectionUtil;
import com.ex.Objects.Keepers;
import com.ex.DAO.SqlDatabaseKeepers;
import com.ex.main.Runner;
import com.ex.main.Screen;
import java.util.List;
import java.util.Scanner;

/*Class Description:
    *The KeeperScreeningScreen is used to gather the username and password of the keeper.
    *The user input is compared to the postgres database table to find a match
    * The matched username and password is used to pull the Keeper name to greet them and send them the the KeeperAccess Screen
*/

public class KeeperScreeningScreen implements Screen {//Start of KeeperScreeningScreen Class

//Instant Variables
    private Scanner s = new Scanner(System.in);
    private String user,pass;
    private GetEnvironmentVar getVar = new GetEnvironmentVar();

//Constructor
    public KeeperScreeningScreen(){}

//Methods
   public Screen doScreen(Runner anInterface) {//Start of doScreen Method
       Runner connectionUtils = new PostgresConnectionUtil(getVar.getUrl(),getVar.getUsername(),getVar.getPassword(),getVar.getSchema());
         DAO<Keepers> keeperRepo = new SqlDatabaseKeepers(connectionUtils);

         List<Keepers> allKeepers = keeperRepo.findAll();//Invoke the SqlDatabaseKeepers findAll method

         System.out.println("Keeper Username:");
            user= s.nextLine();

         System.out.println("Password:");
            pass = s.nextLine();

             for(Keepers k: allKeepers)
             {
                 if(user == null || pass == null){
                     break;
                 }
                 if(user.equals(k.getUsernameKey())){//Compare user input with database returns
                     if(pass.equals(k.getPasswordKey())){
                         System.out.println("Hello "+k.getFirstname()+" "+k.getLastname());
                         return new KeeperAccess(user);
                     }
                 }
             }

        System.out.println("User Not Found");
   return new KeeperScreeningScreen();


//OLD FILE IO CODE REPLACED NOW UNUSED
//        int row = 0;
//        FileIoDAO fileIoDAO = ((KeeperGuestSorter) anInterface).getPasswordKeeper(row);
//
//        for(row=0; row<16; row=row+2) {
//
//           String username = fileIoDAO.getUserAndPassword(row);
//           String password =fileIoDAO.getUserAndPassword(row+1);
//        }
     }//End of doScreen Method

}//End of KeeperScreeningScreen Class
