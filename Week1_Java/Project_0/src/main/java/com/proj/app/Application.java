package com.proj.app;

import java.io.IOException;

public abstract class Application {

    protected String title;

    // what does run do?
    public abstract void run() throws IOException;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
