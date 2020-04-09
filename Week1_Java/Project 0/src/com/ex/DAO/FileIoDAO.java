package com.ex.DAO;

import com.ex.MainAndMenu.Runner;

import java.io.*;

public class FileIoDAO implements DAO {
    String[][] animalInventory;

    public FileIoDAO(String answerFilePath) throws IOException {
        FileReader reader = null;
        BufferedReader bReader = null;
       animalInventory = new String[100][5];

        try {
            reader = new FileReader(answerFilePath); // this reads a file character-by-character
            bReader = new BufferedReader(reader); // this will allow to read the stream line-by-line

            String line = " ";
            int i= 0,j=0;
            while(j<=4) {
                while ((line = bReader.readLine()) != null) { // read each line until EOF
                    animalInventory[i][j++] = line; // add the new line to the answers array
                }
            }


        } catch (FileNotFoundException e) {
            System.err.println("Error loading answer file, using backup");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String[][] getAnimalInventory()
    {
        return animalInventory;
    }

    @Override
    public DAO doDAO(Runner menu) {
        return null;
    }
}


