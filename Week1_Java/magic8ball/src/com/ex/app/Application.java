package com.ex.app;

public abstract class Application {

    private String title;
    // what does run do?
    public abstract void run();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
