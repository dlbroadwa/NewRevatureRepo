package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.Magic8Ball;
import com.ex.app.Magic8BallApplication;

import java.util.Scanner;

public class AnswerScreen implements Screen {
    private String question;
    private Magic8Ball magic8Ball;
    private String[] answers = new String[]{
      "Yes",
      "No",
      "Maybe",
      "It doesn't matter",
      "Ask again later",
      "Like I care",
      "Outlook not so good",
      "Outlook is great"
    };

    public AnswerScreen(String input){
        question = input;
    }

    public Screen doScreen(Application app) {
        magic8Ball = new Magic8Ball(7, 0);
        System.out.println(question + " has been received by the 8 Ball");
        System.out.println(answers[magic8Ball.shake()]);

        return null;
    }

}
