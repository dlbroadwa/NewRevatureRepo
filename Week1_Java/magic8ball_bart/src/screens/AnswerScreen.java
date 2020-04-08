package screens;

import app.Application;
import app.Magic8Ball;
import app.Magic8BallApplication;

public class AnswerScreen implements Screen {

	private String question = "Default";
	
	public AnswerScreen(String question) {
		this.question = question;
	}
	
	@Override
	public Screen doScreen(Application app) {
		// TODO Auto-generated method stub
		System.out.println("You asked the 8 ball " + question);
		String[] messages = new String[]{"All signs point to yes!", "Maybe.", "Outlook not so good." , "Not likely at all.", "Ask again later."};
		Magic8Ball magic8Ball = ((Magic8BallApplication)app).getMagic8Ball();
		int resultIndex = magic8Ball.shake();
		if (resultIndex >= messages.length) {
			resultIndex = messages.length - 1;
		}
		System.out.println("Answer: " + messages[resultIndex]);
		return null;
	}

}
