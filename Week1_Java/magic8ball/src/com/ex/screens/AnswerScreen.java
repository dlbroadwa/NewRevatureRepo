package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.Magic8BallApplication;

import java.util.Scanner;

public class AnswerScreen implements Screen {
	String question;

	public AnswerScreen(String question) {
		this.question = question;
	}
	
	public Screen doScreen(Application app) {
		boolean loop = true;
	    Scanner scanner = ((Magic8BallApplication)app).getScanner();
	    System.out.println("\nThe answer to your questions is: " + ((Magic8BallApplication)app).getMagic8Ball().getMessage());
	    System.out.println("\nWould you like to ask another question? (y/n)");
	    do {
	    	String input = scanner.nextLine();
	    	if (input.equals("y")) {
	    		System.out.println();
	    		return new QuestionScreen();
	    	} else if (input.equals("n")) {
		    	System.out.println("\nYou set the Magic 8 Ball down. Forever.");
		    	System.exit(0);
		    }    	
	    } while (loop);
	    return null;
	  }
}
