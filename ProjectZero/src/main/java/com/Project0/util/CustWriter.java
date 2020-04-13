package com.Project0.util;

import com.Project0.model.Golfer;
import com.Project0.model.User;

import java.io.*;

/* this class is temporary use for File I/O while we are on this phase of the project */
public class CustWriter {

    public void newGolfer(Golfer golfer) throws Exception {
        File golferFile = new File("src/main/resources/Golfers");
        try{
            FileWriter sb = new FileWriter("src/main/resources/Golfers", true);
            sb.append(Long.toString(golfer.getUserID()));
            sb.append(',');
            sb.append(golfer.getName());
            sb.append(',');
            sb.append(golfer.getAddress());
            sb.append(',');
            sb.append(golfer.getPhone());
            sb.append(',');
            sb.append(golfer.getEmergencyPhone());
            sb.append(',');
            sb.append(golfer.getCarMake());
            sb.append(',');
            sb.append(golfer.getCarModel());
            sb.append(',');
            sb.append(golfer.getCarLicensePlate());
            sb.append('\n');

            sb.flush();
            sb.close();
            System.out.println("done!");
        }catch (IOException e){
            e.printStackTrace();
            throw new Exception("Failed to update csv file");
        }
    }
    
    public void updateGolfer(Golfer oldGolfer, Golfer newGolfer) throws Exception {
        File golferFile = new File("src/main/resources/Golfers");
        String oldContent = "";
        String oldString = oldGolfer.getUserID() +
                "," + oldGolfer.getName() +
                "," + oldGolfer.getAddress() +
                "," + oldGolfer.getPhone() +
                "," + oldGolfer.getEmergencyPhone() +
                "," + oldGolfer.getCarMake() +
                "," + oldGolfer.getCarModel() +
                "," + oldGolfer.getCarLicensePlate();
        String newString = newGolfer.getUserID() +
                "," + newGolfer.getName() +
                "," + newGolfer.getAddress() +
                "," + newGolfer.getPhone() +
                "," + newGolfer.getEmergencyPhone() +
                "," + newGolfer.getCarMake() +
                "," + newGolfer.getCarModel() +
                "," + newGolfer.getCarLicensePlate();
        //System.out.println(newString);   //DEBUG REPLACELINE
                
        BufferedReader bReader = null;
        FileWriter fWriter = null;
        
        try{
            bReader = new BufferedReader(new FileReader(golferFile));
            //read all lines & put into oldContent
            String line = bReader.readLine();
            while(line != null) {
                oldContent = oldContent + line + System.lineSeparator();
                line = bReader.readLine();
            }
            //replace oldString with newString in oldContent
            String newContent = oldContent.replaceAll(oldString, newString);
            if(newContent.length() <= 0)
                System.out.println("NOTHING CHANGED");
            //System.out.println(newContent);   //DEBUG FOR NEW CONTENT TO BE WRITTEN
            //rewrite file with new content
            fWriter = new FileWriter(golferFile);
            fWriter.write(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bReader.close();
                fWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateUserPassword(User user, String newHashedPassword) throws Exception{
        File userFile = new File("src/main/resources/Users");
        String oldContent = "";
        String oldString = user.getUsername() +
                "," + user.getPassword() +
                "," + user.getAccessLevel();
        String newString = user.getUsername() +
                "," + newHashedPassword +
                "," + user.getAccessLevel();

        BufferedReader bReader = null;
        FileWriter fWriter = null;

        try{
            bReader = new BufferedReader(new FileReader(userFile));
            //read all lines & put into oldContent
            String line = bReader.readLine();
            while(line != null) {
                oldContent = oldContent + line + System.lineSeparator();
                line = bReader.readLine();
            }
            //replace oldString with newString in oldContent
            String newContent = oldContent.replaceAll(oldString, newString);
            if(newContent.length() <= 0)
                System.out.println("NOTHING CHANGED");
            System.out.println(newContent);
            //rewrite file with new content
            fWriter = new FileWriter(userFile);
            fWriter.write(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bReader.close();
                fWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
