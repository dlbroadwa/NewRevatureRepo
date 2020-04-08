package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.Magic8Ball;

public class AnswerScreen implements Screen {

    private Magic8Ball magic8Ball;

    @Override
    public Screen doScreen(Application app) {
        String[] messages = new String[]{"All signs point to yes", "Outlook not great so maybe stop?", "I don't really have an opinion", "Did you really ask that?", "Maybe, Maybe Not. Are really caring what I say?"};
        System.out.println(messages[magic8Ball.shake()]);
        return null;
    }

    public AnswerScreen(){
        magic8Ball = new Magic8Ball(3, 0);
    }

    public Magic8Ball getMagic8Ball() {
        return magic8Ball;
    }
}
