package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.Magic8Ball;
import com.ex.app.Magic8BallApplication;

public class AnswerScreen implements Screen {

    private String question;

    AnswerScreen(String question) {
        this.question = question;
    }

    @Override
    public Screen doScreen(Application app) {
        Magic8Ball mb = ((Magic8BallApplication)app).getMagic8Ball();
        System.out.println("Your question \"" + question + "\" is:\n");
        System.out.println(mb.getMessage(mb.shake()));
        return null;
    }
}
