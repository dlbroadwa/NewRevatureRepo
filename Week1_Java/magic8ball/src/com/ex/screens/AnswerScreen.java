package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.Magic8Ball;

public class AnswerScreen implements Screen {

    private Magic8Ball magic8Ball;

    // array containing 8ball answers
    private String[] message = {"As I see it, yes.\n",
            " Ask again later.\n",
            " Better not tell you now.\n",
            " Cannot predict now.\n",
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

    @Override
    public Screen doScreen(Application app) {
        magic8Ball = new Magic8Ball(message.length,0);

        System.out.println(this.message[magic8Ball.shake()]);
        return null;
    }
}
