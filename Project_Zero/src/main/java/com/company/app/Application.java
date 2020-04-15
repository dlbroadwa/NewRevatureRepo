package com.company.app;

public abstract class Application {
    protected String title;

    public abstract void run();

    public String getTitle() {return title;}

    public void setTitle(String title) {
        this.title = title;
    }

}
