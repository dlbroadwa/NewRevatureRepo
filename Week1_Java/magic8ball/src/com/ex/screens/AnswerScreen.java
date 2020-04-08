package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.Magic8Ball;
import com.ex.app.Magic8BallApplication;

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
}
