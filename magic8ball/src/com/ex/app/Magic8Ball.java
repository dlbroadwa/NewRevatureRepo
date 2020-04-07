package com.ex.app;

import com.ex.shaker.Shaker;

import java.util.Random;

public class Magic8Ball implements Shaker {
  private int currentMessageIndex;
  private String[] messages = {"Yes", "NO", "Maybe", "Ask again later", "Don't bother!", "Go ahead!", "Why not?", "GO AWAY!!!"};

  public Magic8Ball() {}

  public Magic8Ball(String... messages) {
    this.messages = messages;
  }

  private int getMaxRandNum() {
    System.out.println(messages.length);
    return messages.length;
  }

  public String getMessage() {
    return messages[currentMessageIndex];
  }

  @Override
  public void shake() {
    Random r = new Random(System.nanoTime());
    currentMessageIndex = r.nextInt(getMaxRandNum());
    System.out.println(currentMessageIndex);
  }
}
