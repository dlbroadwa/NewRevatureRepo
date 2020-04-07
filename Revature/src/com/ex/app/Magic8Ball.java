package com.ex.app;


import com.ex.shaker.Shaker;

import java.util.Random;

public class Magic8Ball implements Shaker {

	String[] messages = new String[] {"your so funny", "lets all code", "we are having fun", "Making money is more important than fun"};
	
	
	
	
	public String shake() {
		// TODO Auto-generated method stub
		
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
	
	/*
	 * private int randMin; private int randMax;
	 * 
	 * public Magic8Ball() {}
	 * 
	 * public Magic8Ball(int randMax, int randMin) { this.randMax = randMax;
	 * this.randMin = randMin; }
	 * 
	 * @Override public int shake() { Random r = new Random(); return
	 * r.nextInt((randMax - randMin) + 1) + randMin; }
	 * 
	 * public int getRandMin() { return randMin; }
	 * 
	 * public void setRandMin(int randMin) { this.randMin = randMin; }
	 * 
	 * public int getRandMax() { return randMax; }
	 * 
	 * public void setRandMax(int randMax) { this.randMax = randMax; }
	 */
}
