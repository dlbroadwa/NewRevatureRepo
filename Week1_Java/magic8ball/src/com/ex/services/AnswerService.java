package com.ex.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AnswerService {
    String[] answers = null;

    public AnswerService(String answerFilePath) throws IOException {
        FileReader reader = null;
        BufferedReader bReader = null;
        answers = new String[100];

        try {
            reader = new FileReader(answerFilePath); // this reads the file
            bReader = new BufferedReader(reader);

            String line = "";
            int index = 0;
            while((line = bReader.readLine()) != null) { // read each line until EOF
                answers[index++] = line; //add the new line to the answers array
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error loading answer file, using backup");
            answers = new String[]{"Ask again later"};
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getAnswer(int index) {
        return null;
    }
}
