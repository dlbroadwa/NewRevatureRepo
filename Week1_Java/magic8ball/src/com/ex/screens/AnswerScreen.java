package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.Magic8Ball;
import com.ex.app.Magic8BallApplication;

public class AnswerScreen implements Screen {

	private String question;
	private String[] answers = {"All signs point to yes.", "Outlook not so good.", "Ask again later.", "Without a doubt."};
	
	AnswerScreen (String question){
		this.question = question;
	}
	
	@Override
	public Screen doScreen(Application app) {
		Magic8Ball ball = ((Magic8BallApplication)app).getMagic8Ball();
		System.out.println("You asked: " + question);
		System.out.println("Magic 8 Ball says: " + answers[ball.shake()]);
		
		return null;
	}
	
}
