package com.ex.app;

import java.util.Random;
import com.ex.shaker.*;

public class Magic8Ball implements Shaker {
	private int randMin;
	private int randMax;
	
	public Magic8Ball() {
		randMin = 0;
		randMax = 3;
	}
	
	public Magic8Ball(int randMax, int randMin) {
		this.randMax = randMax;
		this.randMin = randMin;
	}
	
	public int shake() {
		Random r = new Random();
		return r.nextInt(randMax - randMin + 1) + randMin;
	}
}
