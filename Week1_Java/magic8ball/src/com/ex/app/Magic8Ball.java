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
  public String shake() {
    Random r = new Random();
    int randNum = r.nextInt((randMax - randMin) + 1) + randMin;
    if (randNum == 1){
      return "This the Way!";
    } else if (randNum == 2){
      return "I have Spoken";
    } else if (randNum == 3) {
      return "Go Ask Baby Yoda";
    }
    else return "I have no Idea";
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
