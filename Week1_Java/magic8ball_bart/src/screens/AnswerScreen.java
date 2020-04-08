package screens;

import services.AnswerService;
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
		Magic8Ball magic8Ball = ((Magic8BallApplication)app).getMagic8Ball();
		AnswerService answerService = ((Magic8BallApplication)app).getAnswerService();
		
		System.out.println("You asked the 8 ball " + question);
//		String[] messages = new String[]{"All signs point to yes!", "Maybe.", "Outlook not so good." , "Not likely at all.", "Ask again later."};
//		Magic8Ball magic8Ball = ((Magic8BallApplication)app).getMagic8Ball();
//		int resultIndex = magic8Ball.shake();
//		if (resultIndex >= messages.length) {
//			resultIndex = messages.length - 1;
//		}
//		System.out.println("Answer: " + messages[resultIndex]);
		
		

	    // get my answer index from the 8 ball
	    int index = magic8Ball.shake();
	    String answer = answerService.getAnswer(index);
	    System.out.println(answer);
		
		return null;
	}

}
