package Screens;

import clients.UsersService;
import data.BankUsersInSQLRepo;
import data.IBankUsers;
import model.Users;
import utilities.ConnectionUtils;
import utilities.PostgresConnectionUtil;

import java.util.Scanner;

public class RetsetPinPasswordMenu {
    private String newPassword;

    public RetsetPinPasswordMenu(){
        newPassword = "default";
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Users askNewPassword (){
        //database connection details
        ConnectionUtils connectionUtils = new PostgresConnectionUtil(
                "jdbc:postgresql://project0nathan.crq2hafydb4f.us-east-2.rds.amazonaws.com:5432/postgres",
                "nathan", "nathannguyen", "myschema");


        IBankUsers<Users, String> IBankUsers = new BankUsersInSQLRepo(connectionUtils) ;
        UsersService usersService = new UsersService(IBankUsers);

        Scanner sc = new Scanner(System.in);
        String tmpPin = null;
        int count = 0;

        System.out.println("Enter user email to change user pin: ");
        String email = sc.next();
        email = email.trim();
        Users tmpUser = usersService.findUserByEmail(email);

        if (tmpUser.getEmail_address().equals(email)){
            System.out.println("Please enter your pin (8 character max): ");
            do{
                tmpPin = sc.next();
                if (tmpPin.length() > 8){
                    tmpPin = null;
                    count++;
                    if (count > 4){
                        System.out.println("Bad pin entered " + count + " times.");

                        break;
                    }
                    System.out.println("Enter pin less than 8 characters: ");
                }
                else{
                    break;
                }
            }while (true);
        }

        if (tmpPin != null){
            tmpUser.setUser_pin(tmpPin);
        }



        return tmpUser;
    }


}
