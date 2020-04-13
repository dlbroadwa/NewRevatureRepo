package com.ex.keepers;

import com.ex.dao.FileIoDAO;
import com.ex.guests.GuestAccess;
import com.ex.keepers.KeeperAccess;
import com.ex.main.KeeperGuestSorter;
import com.ex.main.Runner;
import com.ex.main.Screen;


import java.util.Scanner;

public class KeeperScreeningScreen implements Screen {
    private Scanner s = new Scanner(System.in);
    private GuestAccess guestAccess=new GuestAccess();
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
                    return new KeeperAccess();
                    }
                }
            }
            System.out.println("User Not Found");
            return null;
        }

}
