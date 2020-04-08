package com.ex.services;

import java.io.*;
import java.util.ArrayList;

public class AnswerService {
  ArrayList<String> answers = null;

  public AnswerService(String answerFilePath) {
    FileReader reader = null;
    BufferedReader bReader = null;
    answers = new ArrayList<>();

    try {
      reader = new FileReader(answerFilePath); // this reads a file character-by-character
      bReader = new BufferedReader(reader); // this will allow to read the stream line-by-line
      String line = "";
      int index = 0;
      while((line = bReader.readLine()) != null) { // read each line until EOF
        answers.add(line); // add the new line to the answers array
        index++;
      }


    } catch (FileNotFoundException e) {
      System.err.println("Error loading answer file, using backup");
      answers = new ArrayList<String>(){};
      answers.add("Not likely");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public ArrayList<String> getAnswer(int index) {
    // make sure to handle index bounds
    return this.answers;
  }
}
