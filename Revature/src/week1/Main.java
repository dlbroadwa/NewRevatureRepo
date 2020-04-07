package week1;

import java.util.Random;

public class Main {

		//procedural programming concepts
		//data types
		// *functions*
		//logic branches
		//loops 
	
	String[] messages = new String[] {"All signs point to yes", "Outlook not so good", "Ask again later"};
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Main m = new Main();
		
		String myMessage = m.shakeBall();
			
		m.printMessage(myMessage);
			
	}
	
	private String shakeBall()
	{
		int index = getMessageIndex();
		
		if(index < 0) {
			index = 0;
		}
		else if(index >= messages.length) {
			index = messages.length - 1;
		}
	
		return messages[index];
		
		
	}
	
	private int getMessageIndex() {
		
		Random r = new Random();
		return r.nextInt( (messages.length - 0) + 1) + 0;
	}
	
	private String getMessage() {
		return "All signs point to yes!";
	}
	
	private void printMessage(String msg) {
		System.out.println(msg);
	}

}
