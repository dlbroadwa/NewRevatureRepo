package com.project0.Util;

import com.project0.model.Golfer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class CustWriter {

    public void newGolfer(Golfer golfer) throws Exception {
        File GolfersCSV = new File("Golfers.csv");
        if(!GolfersCSV.exists()){
            try{
                FileWriter sb = new FileWriter("Golfers.csv");
                sb.append("userID");
                sb.append(',');
                sb.append("name");
                sb.append(',');
                sb.append("address");
                sb.append(',');
                sb.append("phone");
                sb.append(',');
                sb.append("emergencyPhone");
                sb.append(',');
                sb.append("carMake");
                sb.append(',');
                sb.append("carModel");
                sb.append(',');
                sb.append("carLicensePlate");
                sb.append('\n');
            } catch (IOException e){
                e.printStackTrace();
                throw new Exception("Failed to create new csv file");
            }
        }
        try{
            FileWriter sb = new FileWriter("Golfers.csv", true);
            sb.append((char) golfer.getUserID());
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
}
