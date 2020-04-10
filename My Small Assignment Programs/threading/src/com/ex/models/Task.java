package com.ex.models;

public class Task implements Runnable{
  private String name;

  public Task(String name) {
    this.name = name;
  }
  public String getName(){ return name; }
  @Override
  public void run() {
    for(int i = 0; i < 10; i++) {
      System.out.println("performing task " + name);

      try {
        Thread.sleep(4000);
      } catch (InterruptedException ex) {
        ex.printStackTrace();
        break;
      }
    }
  }
}
