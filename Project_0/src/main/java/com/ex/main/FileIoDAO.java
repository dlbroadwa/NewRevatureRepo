
//OLD FILE IO CODE REPLACED NOW UNUSED

//package com.ex.keeper_dao;
//
//import java.io.*;
//
//public class FileIoDAO {
//    String[] info;
//
//    public FileIoDAO(String animalFilePath){
//        FileReader reader = null;
//        BufferedReader bReader = null;
//        info = new String[100];
//
//        try {
//            reader = new FileReader(animalFilePath); // this reads a file character-by-character
//            bReader = new BufferedReader(reader); // this will allow to read the stream line-by-line
//
//            String line = "";
//            int row = 0,column = 0;
//                while ((line = bReader.readLine()) != null) { // read each line until EOF
//                    info[row++] = line; // add the new line to the info array
//            }
//
//        } catch (FileNotFoundException e) {
//            System.err.println("Error loading answer file, using backup");
//            info[0] = "Sorry the Zoo seems to have lost the information";
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//    public String getAnimalInventory(int row) {
//         return info[row];
//    }
//
//    public String getUserAndPassword(int row){
//        return info[row];
//    }
//
//}


