package com.company.DAO;

import java.sql.Connection;

public class UserPass {
    //check existing user and password combos
    public static boolean UserPassCheck(String user, String pass){
        //select from the userandpw table
        String sqlQuery = "Select username, pass from public.userandpw where username='"+user+"'";



        return true;
    }

    //add new user and pw


}
