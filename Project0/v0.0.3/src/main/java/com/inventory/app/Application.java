package com.inventory.app;

public abstract class Application {
    Application(String title){
        this.title = title;
    }

    private String title;

    public abstract void run();
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Application{" +
                "title='" + title + '\'' +
                '}';
    }
}
