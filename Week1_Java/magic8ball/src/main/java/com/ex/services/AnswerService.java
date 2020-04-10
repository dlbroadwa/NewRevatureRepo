package com.ex.services;

import java.io.*;

public class AnswerService {
  String[] answers = null;

  public AnswerService(String answerFilePath) {
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

  public String getAnswer(int index) {
    // make sure to handle index bounds
    return answers[index];
  }
}
