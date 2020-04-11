package com.Project0.util;

import com.Project0.model.Golfer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/* this class is temporary use for File I/O while we are on this phase of the project */
public class CustWriter {

    public void newGolfer(Golfer golfer) throws Exception {
        File GolfersCSV = new File("src/main/resources/Golfers");
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
}
