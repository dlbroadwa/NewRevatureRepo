package com.ex.app;

import com.ex.shaker.Shaker;

import java.util.Random;

public class Magic8Ball implements Shaker {
  private int randMin;
  private int randMax;
  private String[] messages = {"yes", "no", "maybe"};

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

  public void setMessages(String[] messages) {
    this.messages = messages;
  }

  public String getMessage(int index) {
    if (index < 0 || index >= messages.length) throw new IndexOutOfBoundsException();
    return this.messages[index];
  }

}
