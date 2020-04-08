package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.Magic8BallApplication;

import java.util.Scanner;

public class AnswerScreen implements Screen {

    String answer;
    @Override
    public Screen doScreen(Application app) {
        System.out.println("You asked the 8 Ball " + getAnswer());
        return null;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
