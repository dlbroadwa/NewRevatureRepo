package com.ex.app;

import com.ex.shaker.Shaker;

import java.util.ArrayList;
import java.util.Random;

public class Magic8Ball implements Shaker {
  private int randMin;
  private int randMax;
  //private ArrayList messages;

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
