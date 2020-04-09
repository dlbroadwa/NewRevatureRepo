package com.ex.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AnswerService {
    ArrayList<String> answers;

    public AnswerService(String answerFilePath) {
        FileReader reader;
        BufferedReader bReader;
        answers = new ArrayList<String>();

        try {
            reader = new FileReader(answerFilePath); // Reads a file character-by-character
            bReader = new BufferedReader(reader); // allows for reading stream line by line

            String line = "";
            int index = 0;
            while ((line = bReader.readLine()) != null) { // read each line until EOF
                //answers[index++] = line; // add the new line to the answers array
                answers.add(line);
            }
        }
        catch (FileNotFoundException e) {
            System.err.println("Error loading answer file, using backup");
            answers.add("Ask again later");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getAnswer(int index) {
        if (index < 0) index = 0;
        else if (index >= answers.size()) index = answers.size() - 1;
        return answers.get(index);
    }

    public int getNumAnswers() {
        return answers.size();
    }
}
