package com.ex.app;

// concrete class
// must implement application
public class Magic8BallApplication extends Application {

    private Magic8Ball magic8Ball;
    private String title;
    private String[] message = {"Nah", "This ain't it chief", "Big Yes", "Maybe", "Rethink that", "Absolutely"};

    public Magic8BallApplication(){
        magic8Ball = new Magic8Ball(0, message.length);
    }

    public Magic8BallApplication(String title, String[] message ){
        this();
        this.title = title;
        this.message = message;
    }
    @Override
    public void run() {
        System.out.println(this.message[magic8Ball.shake()]);
    }
}
