package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.Magic8Ball;
import com.ex.app.Magic8BallApplication;
<<<<<<< HEAD

import java.util.Scanner;

public class AnswerScreen implements Screen {

    String answer;

    private String [] responses = {"No", "Yes", "Maybe"};

    Magic8Ball magic8Ball = new Magic8Ball(responses.length - 1, 0);
    public AnswerScreen (String answer)
    {
        this.answer = answer;
    }

    @Override
    public Screen doScreen(Application app) {
        System.out.println("You asked the 8 Ball " + getAnswer());
        System.out.println(responses[magic8Ball.shake()]);
        return null;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
=======
import com.ex.services.AnswerService;

public class AnswerScreen implements Screen{
  @Override
  public Screen doScreen(Application app) {
    String currentQuestion = ((Magic8BallApplication) app).getCurrentQuestion();
    Magic8Ball magic8Ball = ((Magic8BallApplication)app).getMagic8Ball();
    AnswerService answerService = ((Magic8BallApplication)app).getAnswerService();

    // get my answer index from the 8 ball
    int index = magic8Ball.shake();
    String answer = answerService.getAnswer(index);

    System.out.println("You asked \n" + currentQuestion);
    System.out.println(answer);
    return null;
  }
>>>>>>> f7c50f4eff8e34b6d3503e4858908e96de2832bf
}
