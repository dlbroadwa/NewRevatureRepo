package app;
import java.util.Random;

import Shaker.Shaker;

public class Magic8BBall implements Shaker{

	 private int randMin;
	  
	 private int randMax;

	  public Magic8BBall() {}

	  public Magic8BBall(int randMax, int randMin) {
	    
		this.randMax = randMax;
	  
	    this.randMin = randMin;
	  
	  }

	  @Override
	  public int shake() {
	    
		Random r = new Random();
	  
	    return r.nextInt((randMax - randMin) + 1) + randMin;
	  
	  }

	  public int getRandMin() {
	  
		  return randMin;
	  
	  }

	  public void setRandMin(int randMin) {
	  
		  this.randMin = randMin;
	  
	  }

	  public int getRandMax() {
	  
		  return randMax;
	  
	  }

	  public void setRandMax(int randMax) {
	    
		  this.randMax = randMax;
	
	}

}

