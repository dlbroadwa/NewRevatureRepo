package com.ex.app;

// Concrete Class: has shape and function
// Must impl Appllication
public class Magic8BallApplication extends Application
{
    protected String title;
    private Magic8Ball magic8Ball;

    public Magic8BallApplication()
    {
        magic8Ball = new Magic8Ball(0, 3);
    }

    public Magic8BallApplication(String title)
    {
        // call the no-args constructor to setup the magic8ball without repeating code
        this();
        this.title = title;
    }
    @Override
    public void run()
    {
        System.out.println(magic8Ball.shake());
    }

    public String getTitle()
    {
        return this.title;
    }
}
