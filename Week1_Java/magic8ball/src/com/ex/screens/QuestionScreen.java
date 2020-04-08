package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.Magic8BallApplication;

import java.util.Scanner;
import java.util.SortedMap;

public class QuestionScreen implements Screen {



  @Override
  public Screen doScreen(Application app) {
    Scanner scanner = ((Magic8BallApplication)app).getScanner();
<<<<<<< HEAD
    System.out.println("Ask the 8 ball anything");
    String input = scanner.nextLine();
    AnswerScreen newAnswer = new AnswerScreen(input);
    return newAnswer;
=======

    while(true) {
      System.out.println("Ask the 8 ball anything");
      String input = scanner.nextLine();

      if(input.length() == 0 || input.trim().equals("")) {
        continue;
      } else if (input.equals("\\q")) {
        break;
      }else {
        ((Magic8BallApplication) app).setCurrentQuestion(input);
        return new AnswerScreen();
      }
    }
    return null;
>>>>>>> f7c50f4eff8e34b6d3503e4858908e96de2832bf
  }
}
