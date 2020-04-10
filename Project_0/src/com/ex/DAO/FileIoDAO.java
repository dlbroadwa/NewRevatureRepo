package com.ex.DAO;

import com.ex.MainAndMenu.Runner;
import com.ex.MainAndMenu.Screen;

import java.io.*;
import java.util.List;

public class FileIoDAO implements Screen {
    String[] animalInventory;

    public FileIoDAO(String answerFilePath){
        FileReader reader = null;
        BufferedReader bReader = null;
        animalInventory = new String[100];

        try {
            reader = new FileReader(answerFilePath); // this reads a file character-by-character
            bReader = new BufferedReader(reader); // this will allow to read the stream line-by-line

            String line = "";
            int index = 0;
            while((line = bReader.readLine()) != null) { // read each line until EOF
                animalInventory[index++] = line; // add the new line to the answers array
            }


        } catch (FileNotFoundException e) {
            System.err.println("Error loading answer file, using backup");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String[] getAnimalInventory() {
        return animalInventory;
    }

    public Screen doScreen(Runner anInterface) {
        return null;
    }
//    @Override
//    public Integer save(Object o) {
//        return null;
//    }
//
//    @Override
//    public Object getById(Integer id) {
//        return null;
//    }
//
//    @Override
//    public void update(Object o, Integer id) {
//
//    }
//
//    @Override
//    public void delete(Object o) {
//
//    }
//
//    @Override
//    public List<Object> getAll() {
//        return null;
//    }
//

}


