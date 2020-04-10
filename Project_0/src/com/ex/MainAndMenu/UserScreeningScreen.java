package com.ex.MainAndMenu;

import com.ex.DAO.FileIoDAO;

import java.util.Scanner;

public class UserScreeningScreen implements Screen {
    private Scanner s = new Scanner(System.in);
    private String user,pass;
        public Screen doScreen(Runner anInterface) {
           int row = 0;
            FileIoDAO fileIoDAO = ((KeeperGuestSorter) anInterface).getPasswordKeeper(row);
            System.out.println("Keeper Username:");
            user= s.nextLine();
            System.out.println("Password:");
            pass = s.nextLine();
            for(row=0; row<16; row=row+2) {
                String username = fileIoDAO.getUserAndPassword(row);
                String password =fileIoDAO.getUserAndPassword(row+1);
                if(user == null || pass == null)
                {
                    break;
                }
                if(user.equals(username)){
                    if(pass.equals(password)){
                    return new InventoryScreen();
                    }
                }
            }
            System.out.println("User Not Found");
            return null;
        }

}
