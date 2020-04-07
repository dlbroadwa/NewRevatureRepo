package app;

import java.util.Random;

import shaker.Shaker;

public class Magic8Ball implements Shaker {
	private int randMin;
	private int randMax;
	
	public Magic8Ball() {}
	
	public Magic8Ball(int randMax, int randMin) {
		this.randMax = randMax;
		this.randMin = randMin;
	}
	
	public int getRandMin() {
		return randMin;
	}
	
	public int getRandMax() {
		return randMax;
	}
	
	public void setRandMin(int randMin) {
		this.randMin = randMin;
	}
	
	public void setRandMax(int randMax) {
		this.randMax = randMax;
	}
	
	@Override
	public int shake() {
		Random r = new Random();
		return r.nextInt((randMax - randMin) + 1) + randMin;
	}
}
