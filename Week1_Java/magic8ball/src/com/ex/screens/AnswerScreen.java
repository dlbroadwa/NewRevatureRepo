package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.Magic8Ball;

public class AnswerScreen implements Screen {

	private String question;
	private Magic8Ball ball;
	private String[] answers = {"All signs point to yes.", "Outlook not so good.", "Ask again later.", "Without a doubt."};
	
	AnswerScreen (String question, Magic8Ball ball){
		this.question = question;
		this.ball = ball;
	}
	
	@Override
	public Screen doScreen(Application app) {
		System.out.println("You asked: " + question);
		System.out.println("Magic 8 Ball says: " + answers[ball.shake()]);
		
		return null;
	}
	
}
