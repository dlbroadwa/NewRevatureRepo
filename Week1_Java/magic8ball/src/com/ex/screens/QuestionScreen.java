package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.Magic8BallApplication;

import java.util.Scanner;
import java.util.SortedMap;

public class QuestionScreen implements Screen {

  AnswerScreen newAnswer = new AnswerScreen();

  @Override
  public Screen doScreen(Application app) {
    Scanner scanner = ((Magic8BallApplication)app).getScanner();
    System.out.println("Ask the 8 ball anything");
    String input = scanner.nextLine();
    newAnswer.setAnswer(input);
    newAnswer.doScreen(app);
    scanner.next();
    return null;
  }
}
