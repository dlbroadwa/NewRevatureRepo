package com.ex.app;

import java.util.ArrayList;

// Concrete Class: has shape and function
// Must impl Appllication
public class Magic8BallApplication extends Application
{
    protected String title;
    private Magic8Ball magic8Ball;
    private String[] messages = {
            "As I see it, yes", "Ask again later.", "Better not tell you now.", "Cannot predict now",
            "Concentrate and ask again", "Don't count on it", "It is certain", "It is decidedly so",
            "Most likely.", "My reply is no", "My sources say no", "Outlook not so good", "Reply hazy, try again",
            "Signs point to yes", "Very doubtful", "Without a doubt", "Yes", "Yes - definitely", "You may rely on it"
    };
    public Magic8BallApplication()
    {
        magic8Ball = new Magic8Ball(0, messages.length);
    }

    public Magic8BallApplication(String title, String[] messages)
    {
        // call the no-args constructor to setup the magic8ball without repeating code
        this();
        this.title = title;
        this.messages = messages;
    }

    @Override
    public void run()
    {
        System.out.println(this.messages[magic8Ball.shake()]);
    }

    public String getTitle()
    {
        return this.title;
    }
}
