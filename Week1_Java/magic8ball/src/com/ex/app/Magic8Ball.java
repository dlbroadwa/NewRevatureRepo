package com.ex.app;

import com.ex.shaker.Shaker;

import java.util.Random;

public class Magic8Ball implements Shaker {
  private int randMin;
  private int randMax;

  public Magic8Ball() {}

  public Magic8Ball(int randMax, int randMin) {
    this.randMax = randMax;
    this.randMin = randMin;
  }

  @Override
  public int shake() {
    Random r = new Random();
    return r.nextInt((randMax - randMin) + 1) + randMin;
  }
  
  public String getMessage() {
	  String result;
	  int num = shake();
	  
	  switch (num) {
	  case 1:
		  result = "All signs point to yes!";
		  break;
	  case 2:
		  result = "All signs point to no!";
		  break;
	  case 3:
		  result = "Ask again later.";
		  break;
	  case 4:
		  result = "Very doubtful.";
		  break;
	  case 5:
		  result = "My reply is no.";
		  break;
	  case 6:
		  result = "My reply is yes.";
		  break;
	  case 7:
		  result = "It is almost certain.";
		  break;
	  case 8:
		  result = "It will never be.";
		  break;
	  case 9:
		  result = "It cannot be known.";
		  break;
	  case 10:
		  result = "Concentrate, and ask again.";
		  break;
	  default:
		  result = "There is a chance.";
	  }
	  return result;
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
