package com.revature;

import java.io.*;
import java.util.LinkedList;


public class BankUserFileOperations extends BankUserDAO {
    LinkedList<User> userList = null;
    FileReader reader = null;
    BufferedReader bReader = null;
    static String  userFileLocation = "input\\UserList.csv";


    @Override
    LinkedList<User> getAllUser(){
        LinkedList<User> userList = new LinkedList<User>();
        try {
            reader = new FileReader(userFileLocation);
            bReader = new BufferedReader(reader);

            String line = "";
            int index = 0;
            while((line = bReader.readLine()) != null) {
    //            System.out.println(line);
                String[] str = line.split(",");
                boolean tmpUserActive = false;
                if (str[5].equals("1")){
                    tmpUserActive = true;
                }

                User user = new User (str[0], str[1], str[2], str[3], str[4], tmpUserActive);
                userList.add(user);

            }

            bReader.close();

        } catch (FileNotFoundException e) {
            System.err.println("Error loading answer User file");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return userList;
    };

    @Override
    void insertUser(String newFirstName, String newLastName, String newPhoneNumber, String newUserPin, String newUser_id, String new_is_active) {
        FileWriter fw;
        String newLine = newFirstName.trim() + "," + newLastName.trim() + "," + newPhoneNumber.trim() + "," + newUserPin.trim() +  "," + newUser_id.trim() +  "," + new_is_active.trim();
        System.out.println(newLine);

        /* Verify if the user already exists*/
        boolean is_user_exist = verifyUserExistence(newUser_id);

        /* only insert if user doesn't exist on file already*/
        if (!is_user_exist) {
            try {
                fw = new FileWriter(userFileLocation, true);
                BufferedWriter bWriter = new BufferedWriter(fw);
                bWriter.write("\r\n");
                bWriter.write(newLine);


                bWriter.close();
            } catch (IOException e) {
                System.err.println("Error loading answer User file from insertUser method");
                e.printStackTrace();
            }
        }
        else{
            System.out.println("User id already exists: " + newUser_id);
        }
    }

    /*Verify that a User already exists in the system. */
    private boolean verifyUserExistence(String newUser_id) {
        LinkedList<User> allUsers = this.getAllUser();
        boolean user_exists = false;

        for (User s : allUsers){
            if (s.getUser_id().equals(newUser_id)){
                user_exists = true;
                break;
            }
        }

        return user_exists;
    }


    @Override
    void updateUser() {

    }


    public static void main (String args[]){

        BankUserFileOperations bankUserFileOperations = new BankUserFileOperations();

        bankUserFileOperations.getAllUser();

        System.out.println("\nInsert a user: ");
        String newFirstName = "Erica";
        String newLastName = "Williamson";
        String newPhoneNumber = "6789991111";
        String newUserPin = "7235";
        String newUser_id = "id_123436789";
        String new_is_active = "1";


        bankUserFileOperations.insertUser(newFirstName, newLastName, newPhoneNumber, newUserPin, newUser_id, new_is_active);


        bankUserFileOperations.getAllUser();


    }
}
