package com.ex.services;

import com.ex.app.Magic8Ball;

import java.io.*;
import java.util.ArrayList;

public class AnswerService {

  private ArrayList<String> answers = new ArrayList<String>();

  private Magic8Ball magic8Ball;

  public AnswerService(String answerFilePath) {
    FileReader reader = null;
    BufferedReader bReader = null;

    try {
      reader = new FileReader(answerFilePath); // this reads a file character-by-character
      bReader = new BufferedReader(reader); // this will allow to read the stream line-by-line

      String line = "";
      int index = 0;
      while((line = bReader.readLine()) != null) { // read each line until EOF
        answers.add(line); // add the new line to the answers array
      }


    } catch (FileNotFoundException e) {
      System.err.println("Error loading answer file, using backup");
      answers.clear();
      answers.add("Sorry ask again later.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getAnswer() {
    magic8Ball = new Magic8Ball(answers.size(), 0);
    // make sure to handle index bounds
    return answers.get(magic8Ball.shake());
  }
}
