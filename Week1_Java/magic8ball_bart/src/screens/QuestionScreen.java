package screens;

import java.util.Scanner;

import app.Application;
import app.Magic8BallApplication;

public class QuestionScreen implements Screen {

	String question = "Question goes here.";
	
	@Override
	public Screen doScreen(Application app) {
		// TODO Auto-generated method stub
		Scanner scanner = ((Magic8BallApplication)app).getScanner();
	
		System.out.println("Ask the 8 ball anything");
		String input = scanner.nextLine();
		question = "You asked the 8 ball " + input;
		//System.out.println("You asked the 8 ball " + input);
		AnswerScreen answer = new AnswerScreen(input);
		
		return answer;
	}

}
