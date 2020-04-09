package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.Magic8BallApplication;

import java.util.Scanner;

public class QuestionScreen implements Screen {
  @Override
  public void doScreen(Application app) {
    Scanner scanner = ((Magic8BallApplication)app).getScanner();

    System.out.println("Ask the 8 ball anything");
    String input = scanner.nextLine();
    System.out.println("You asked the 8 ball " + input);

    return AnswerScreen();
  }
}
