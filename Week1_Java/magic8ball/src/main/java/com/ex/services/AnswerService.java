package com.ex.services;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class AnswerService {
  //String[] answers = null;
  public ArrayList<String> answers = new ArrayList<>();


  public AnswerService(String answerFilePath) {
    FileReader reader;
    BufferedReader bReader;
    //answers = new String[100];
    //List<String> answers = new ArrayList<String>();

    try {
      reader = new FileReader(answerFilePath); // this reads a file character-by-character
      bReader = new BufferedReader(reader); // this will allow to read the stream line-by-line

      String line = "";
      int index = 0;
      while((line = bReader.readLine()) != null) { // read each line until EOF
        answers.add(index++, line); // add the new line to the answers array
      }


    } catch (FileNotFoundException e) {
      System.err.println("Error loading answer file, using backup");
      answers = new ArrayList<String>() {};
      answers.add("Ask again later");//(ArrayList<String>) Arrays.asList(new String[]{"Ask again later"});

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getAnswer(int index) {
    // make sure to handle index bounds
    return answers.get(index);
  }


}
<<<<<<< HEAD
=======


>>>>>>> bb0ffbd0e93ba31f61c71c64fbb8433c45899d96
