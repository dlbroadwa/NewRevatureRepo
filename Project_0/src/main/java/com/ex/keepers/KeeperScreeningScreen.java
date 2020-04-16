package com.ex.keepers;

import com.ex.dao.PostgresConnectionUtil;
import com.ex.dao.keeper_dao.FileIoDAO;
import com.ex.dao.keeper_dao.Keepers;
import com.ex.dao.keeper_dao.SqlDatabaseKeepers;
import com.ex.dao.keeper_dao.UserDAO;
import com.ex.main.KeeperGuestSorter;
import com.ex.main.Runner;
import com.ex.main.Screen;


import java.util.List;
import java.util.Scanner;

public class KeeperScreeningScreen implements Screen {
    private Scanner s = new Scanner(System.in);
    private String user,pass;


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
                     return new KeeperAccess();
                 }
             }
         }

//        int row = 0;
//        FileIoDAO fileIoDAO = ((KeeperGuestSorter) anInterface).getPasswordKeeper(row);
//
//        for(row=0; row<16; row=row+2) {
//
//           String username = fileIoDAO.getUserAndPassword(row);
//           String password =fileIoDAO.getUserAndPassword(row+1);
//
//
//
//
//
//        }

        System.out.println("User Not Found");
          return new KeeperScreeningScreen();

     }

}
