package com.ex.keepers;

import com.ex.main.PostgresConnectionUtil;
import com.ex.keeper_dao.Keepers;
import com.ex.keeper_dao.SqlDatabaseKeepers;
import com.ex.keeper_dao.UserDAO;
import com.ex.main.Runner;
import com.ex.main.Screen;
import java.util.List;
import java.util.Scanner;

/*The KeeperScreeningScreen is used to gather the username and password of the keeper.
*The user input is compared to the postgres database table to find a match
* The matched username and password is used to pull the Keeper name to greet them and send them the the KeeperAccess Screen
*/

public class KeeperScreeningScreen implements Screen {


//Instant Variables
    private Scanner s = new Scanner(System.in);
    private String user,pass;

//Constructor
    public KeeperScreeningScreen(){}

//Methods
   public Screen doScreen(Runner anInterface) {
         Runner connectionUtils = new PostgresConnectionUtil(
                 "jdbc:postgresql://database-1.cb402pxtppo6.us-east-2.rds.amazonaws.com:5432/postgres",
                 "paityn", "revature", "project_0");
         UserDAO<Keepers,String> keeperRepo = new SqlDatabaseKeepers(connectionUtils);

         List<Keepers> allKeepers = keeperRepo.findAll();

         System.out.println("Keeper Username:");
         user= s.nextLine();

         System.out.println("Password:");
         pass = s.nextLine();

             for(Keepers k: allKeepers)
             {
                 if(user == null || pass == null){
                     break;
                 }
                 if(user.equals(k.getUsernameKey())){
                     if(pass.equals(k.getPasswordKey())){
                         System.out.println("Hello "+k.getFirstname()+" "+k.getLastname());
                         return new KeeperAccess();
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
     }

}
