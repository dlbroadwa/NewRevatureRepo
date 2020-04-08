package com.ex.app;

import com.ex.shaker.Shaker;

import java.util.Random;

public class Magic8Ball implements Shaker {
  private int randMax;

  public Magic8Ball() {}

  public Magic8Ball(int randMax) {
    this.randMax = randMax;
  }

  @Override
  public int shake() {
    Random r = new Random(System.nanoTime());
    return r.nextInt(randMax);
  }

  public int getRandMax() {
    return randMax;
  }

  public void setRandMax(int randMax) {
    this.randMax = randMax;
  }
}
