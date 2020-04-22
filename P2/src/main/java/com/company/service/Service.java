package com.company.service;

import com.company.app.Item;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Service {
    String[] answers = null;

    public Service(String answerFilePath) {
        FileReader reader = null;
        BufferedReader bReader = null;
        answers = new String[100];

        try {
            reader = new FileReader(answerFilePath); // this reads a file character-by-character
            bReader = new BufferedReader(reader); // this will allow to read the stream line-by-line

            String line = "";
            int index = 0;
            while((line = bReader.readLine()) != null) { // read each line until EOF
                answers[index++] = line; // add the new line to the answers array
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error loading answer file, using backup");
            answers = new String[]{"Ask again later"};
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //read file using scanner
    public void fileService(){
        Scanner scan = new Scanner("inventory");

        while(scan.hasNextLine()) {
            System.out.println(scan.nextLine());

        }
    }
    public String getUserInfo(int index) {
        // make sure to handle index bounds
        return answers[index];
    }
    public Item setItem(Item item){

    }
}
