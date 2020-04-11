package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.Magic8BallApplication;

import java.util.Scanner;

public class QuestionScreen implements Screen {

    @Override
    public Screen doScreen(Application app) {
        Scanner scanner = ((Magic8BallApplication)app).getScanner();

        while (true) {
            System.out.print(((Magic8BallApplication)app).getConfig().getQuestionPrompt());
            String input = scanner.nextLine();

            if (input.length() == 0 || input.trim().equals(""))
                continue;
            else {
                ((Magic8BallApplication) app).setCurrentQuestion(input);
                return new AnswerScreen();
            }
        }
    }
}
