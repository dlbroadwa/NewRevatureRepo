package com.williamchung.project0.application;

public abstract class Application {

  protected String title;

  public abstract void run();

  //Getters & Setters
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
}
