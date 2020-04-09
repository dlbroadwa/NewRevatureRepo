package com.ex.models;

public class Employee extends Thread {
  private String name;

  public Employee(Problem workSpecialization, String name) {
    this.name = name;
    run(workSpecialization);
  }

  private synchronized void doWork(Problem problem){
    if(problem.hasTask()){
      System.out.println("Makes working noises.");
      problem.remove();
    }
  }

  public void run(Problem problem) {
    while(problem.hasTask()) {
      doWork(problem);
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
