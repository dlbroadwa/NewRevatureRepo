package com.ex.app;

// concrete class
// must implement application
public class Magic8BallApplication extends Application {

    private Magic8Ball magic8Ball;

    public Magic8BallApplication(){
        magic8Ball = new Magic8Ball(randMax: 3, )
    }

    public Magic8BallApplication(String title){
        this();
        this.title = title;
    }
    @Override
    public void run() {
        System.out.println(magic8Ball.shake());
    }
}
