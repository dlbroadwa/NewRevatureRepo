package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.Magic8BallApplication;

public class AnswerScreen implements Screen {
    private String question;
    AnswerScreen() {
        question = "";
    }
    AnswerScreen(String question) {
        this.question = question;
    }
    @Override
    public Screen doScreen(Application app) {
        System.out.println("You asked the 8 ball: " + question);

        System.out.println("The magic 8 ball answers: " + ((Magic8BallApplication)app).getMessage());

        return null;
    }
}
