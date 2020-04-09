package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.Magic8Ball;
import com.ex.app.Magic8BallApplication;
import com.ex.services.AnswerService;

public class AnswerScreen implements Screen{
  @Override
  public Screen doScreen(Application app) {
    String currentQuestion = ((Magic8BallApplication) app).getCurrentQuestion();
    Magic8Ball magic8Ball = ((Magic8BallApplication)app).getMagic8Ball();
    AnswerService answerService = ((Magic8BallApplication)app).getAnswerService();

    // get my answer index from the 8 ball
    //int index = magic8Ball.shake();

// I made some change to get the Random number from the Question Input If the USER enter a number
    // If there is no Number input there will be only Message at INDEX 0 Will Trigger


    String answer = answerService.getAnswer(((Magic8BallApplication)app).getNumberFromQuestion());

    System.out.println("You asked \n" + currentQuestion);
    System.out.println(answer);
    return null;
  }
}
