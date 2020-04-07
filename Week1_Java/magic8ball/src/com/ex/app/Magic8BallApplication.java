package com.ex.app;

// concrete class
// must implement application
public class Magic8BallApplication extends Application {

    private Magic8Ball magic8Ball;

    private String[] message = {"As I see it, yes.\n",
            " Ask again later.\n",
            " Better not tell you now.\n",
            " Cannot predict now.\n" +
            " Concentrate and ask again.\n",
            " Don’t count on it.\n",
            " It is certain.\n",
            " It is decidedly so.\n",
            " Most likely.\n",
            " My reply is no.\n",
            " My sources say no.\n",
            " Outlook not so good.\n",
            " Outlook good.\n",
            " Reply hazy, try again.\n",
            " Signs point to yes.\n",
            " Very doubtful.\n",
            " Without a doubt.\n",
            " Yes.\n",
            " Yes – definitely.\n",
            " You may rely on it.\n"};

    public Magic8BallApplication(){
        magic8Ball = new Magic8Ball(0, message.length);
    }

    //not sure why I have this here. Unneeded.
    /*public Magic8BallApplication(String[] message ){
        this();
        this.message = message;
    }*/
    @Override
    public void run() {
        System.out.println(this.message[magic8Ball.shake()]);
    }
}
