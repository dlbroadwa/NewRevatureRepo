package com.revature;

import java.io.*;
import java.util.LinkedList;


public class BankUserFileOperations extends BankUserDAO {
    LinkedList<User> userList = null;
    FileReader reader = null;
    BufferedReader bReader = null;
    public final static String  userFileLocation = "input\\UserList.csv";


    @Override
    LinkedList<User> getAllUser(){
        LinkedList<User> userList = new LinkedList<User>();

        try {
            reader = new FileReader(userFileLocation);
            bReader = new BufferedReader(reader);
            String line = "";
            int index = 0;
            while((line = bReader.readLine()) != null) {
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
    boolean insertUser(String newFirstName, String newLastName, String newPhoneNumber, String newUserPin, String newUser_id, String new_is_active) {
        FileWriter fw;
        String newLine = newUser_id.trim() +  "," +newFirstName.trim() + "," + newLastName.trim() + "," + newPhoneNumber.trim() + "," + newUserPin.trim() +  "," +  new_is_active.trim();
        System.out.println(newLine);
        boolean insertSuccess = false;
        /* Verify if the user already exists */
        boolean is_user_exist = findUserId(newUser_id).equals(newUser_id);

        /* Only insert if user doesn't exist on file already*/
        if (!is_user_exist) {
            try {
                fw = new FileWriter(userFileLocation, true);
                BufferedWriter bWriter = new BufferedWriter(fw);
                bWriter.write("\r\n");
                bWriter.write(newLine);
                insertSuccess = true;
                bWriter.flush();
                bWriter.close();
            } catch (IOException e) {
                System.err.println("Error loading answer User file from insertUser method");
                e.printStackTrace();
            }
        }
        else{
            System.out.println("User id already exists: " + newUser_id);
             insertSuccess = false;
        }

        return insertSuccess;
    }

    /*Verify that a User already exists in the system. */
    private String findUserId(String id) {
        LinkedList<User> allUsers = this.getAllUser();
        String userId = "";

        for (User s : allUsers){
            if (s.getUser_id().equals(id)){
                userId = s.getUser_id();

                break;
            }
        }
        return userId;
    }


    /* Assumptions:
    *   1.  You can only update one User field at a time
    *   2.  Integer argument fieldToUpdate are mapped to columns as followed 1 = firstName, 2 = lastName, 3 = phone number, 4 = pin, 5 = user status
    *   3.  Column 0 represents User ID which will not be updated.
    *   4.  There will be no user deletion at the bank, only user de-activation or activation.
    *   5.  Return true means successful update; return false returns unsuccessful updates.
    * */
    @Override
    boolean updateUser(String userId, String newValue, int fieldToUpdate) {
        LinkedList<User> allUsers = this.getAllUser();
        boolean updateSuccess = false;
        for (User user : allUsers){
            if (user.getUser_id().equals(userId)){
                if (fieldToUpdate == 1){
                    if (!user.getFirstName().equals(newValue)) {
                        user.setFirstName(newValue);
                        updateSuccess = true;
                    }
                }
                else if (fieldToUpdate == 2){
                    if (!user.getLastName().equals(newValue)) {
                        user.setLastName(newValue);
                        updateSuccess = true;
                    }
                }
                else if (fieldToUpdate == 3){
                    if (!user.getPhoneNumber().equals(newValue)) {
                        user.setPhoneNumber(newValue);
                        updateSuccess = true;
                    }
                }
                else if (fieldToUpdate == 4){
                    if (!user.getUser_pin().equals(newValue)) {
                        user.setUser_pin(newValue);
                        updateSuccess = true;
                    }
                }
                else if (fieldToUpdate == 5){
                    if (newValue.equals("1")){
                        user.setIs_active(true);
                        updateSuccess = true;
                    }
                    else if (newValue.equals("0")){
                        user.setIs_active(false);
                        updateSuccess = true;
                    }

                }

            }
        }

        if (updateSuccess){
            writeUpdateToFile(allUsers);
        }
        return updateSuccess;
    }

    @Override
    void printAllUsers() {
        String line = "";
        LinkedList<User> allUsers = getAllUser();
        for (User tmpUser : allUsers){
            line = tmpUser.getUser_id() + "," + tmpUser.getFirstName() + "," + tmpUser.getLastName() + "," + tmpUser.getPhoneNumber() + "," + tmpUser.getUser_pin() + ",";
            if (tmpUser.isIs_active()){
                line = line + "1";
            }
            else{
                line = line + "0";
            }

            System.out.println(line);
        }
    }

    private void writeUpdateToFile(LinkedList<User> allUsers) {

        FileWriter fw = null;
        boolean firstLine = true;
        try {
            fw = new FileWriter(userFileLocation, false);
            BufferedWriter bWriter = new BufferedWriter(fw);
            for (User user : allUsers){
                if (!firstLine) {
                    bWriter.write("\r\n");
                }
                else{
                    firstLine = false;
                }
                String userStatus ;
                if (user.isIs_active()){
                    userStatus = "1";
                }
                else{
                    userStatus = "0";
                }
                String line = user.getUser_id() + "," + user.getFirstName() + "," + user.getLastName() + "," + user.getPhoneNumber() + "," + user.getUser_pin() + "," + userStatus ;
                bWriter.write(line);

            }
            bWriter.flush();
            bWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void main (String args[]){

        BankUserFileOperations bankUserFileOperations = new BankUserFileOperations();

        System.out.println("Current User List:");
        bankUserFileOperations.printAllUsers ();
        System.out.println("\nInsert a user: ");
        String newFirstName = "Aug";
        String newLastName = "Mission";
        String newPhoneNumber = "6789991111";
        String newUserPin = "7235";
        String newUser_id = "id_103436789";
        String new_is_active = "1";


        bankUserFileOperations.insertUser(newFirstName, newLastName, newPhoneNumber, newUserPin, newUser_id, new_is_active);



        bankUserFileOperations.updateUser("id_123436789", "Bourne", 2);

        System.out.println("\nCurrent User List:");

        bankUserFileOperations.printAllUsers ();

    }
}
