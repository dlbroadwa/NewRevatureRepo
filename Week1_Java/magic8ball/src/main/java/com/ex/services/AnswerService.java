package com.ex.services;

import java.io.*;
import java.util.ArrayList;

public class AnswerService {
  ArrayList<String> answers = new ArrayList<>();

  public AnswerService(String answerFilePath) {
    FileReader reader = null;
    BufferedReader bReader = null;

    try {
      reader = new FileReader(answerFilePath); // this reads a file character-by-character
      bReader = new BufferedReader(reader); // this will allow to read the stream line-by-line

      String line = "";
      while((line = bReader.readLine()) != null) { // read each line until EOF
        answers.add(line); // add the new line to the answers array
      }
      System.out.printf("ANSWERS LENGTH: %d", getAnswersLength());


    } catch (FileNotFoundException e) {
      System.err.println("Error loading answer file, using backup");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getAnswer(int index) {
    // make sure to handle index bounds
    return answers.get(index);
  }

  public int getAnswersLength() {
    return answers.size();
  }
}


