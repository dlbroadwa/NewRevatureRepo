package com.Project0.util;

import com.Project0.model.Golfer;
import com.Project0.model.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CustReader {

    public User readUserLoginRequest(String filePath, String username, String password) {
        FileReader reader = null;
        BufferedReader bReader = null;

        try{
            reader = new FileReader(filePath);
            bReader = new BufferedReader(reader);

            String line = "";
            while((line = bReader.readLine()) != null) {
                String attributes[] = line.split(",");
                if(attributes[0].equals(username) && attributes[1].equals(password)) {
                    User thisUser = new User(attributes[0], attributes[1], attributes[2]);
                    return thisUser;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading user file");
            return null;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }


    public ArrayList<Golfer> getGolfers(String filePath) {
        FileReader reader = null;
        BufferedReader bReader = null;
        ArrayList<Golfer> golfers = new ArrayList<>();

        try{
            reader = new FileReader(filePath);
            bReader = new BufferedReader(reader);

            String line = "";
            while((line = bReader.readLine()) != null) {
                String attr[] = line.split(",");
                Golfer thisGolfer = new Golfer(Long.parseLong(attr[0]), attr[1], attr[2], attr[3], attr[4], attr[5], attr[6], attr[7]);
                golfers.add(thisGolfer);
            }
            return golfers;
        } catch (FileNotFoundException e) {
            System.out.println("Error loading user file");
            return golfers;
        } catch (IOException ex) {
            ex.printStackTrace();
            return golfers;
        }
    }
}
