package com.ex.models;

public class Employee extends Thread {
  private String name;
  private Problem workType;

  public Employee(Problem workType, String name) {
    this.workType = workType;
    this.name = name;
    start();
  }

  private synchronized void doWork(Problem problem){
    if(problem.hasTask()){
      System.out.println("Makes working noises.");
      problem.remove();
    }
  }

  public void run() {
    while(workType.hasTask()) {
      doWork(workType);
      try {
        Thread.sleep(2000);
      } catch (InterruptedException ex) {
        System.err.println("I was interrupted while doing my work.");
        ex.printStackTrace();
        break;
      }
    }
  }
}
