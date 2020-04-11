package com.ex;

import com.ex.app.Application;
import com.ex.app.Magic8BallApplication;
import com.ex.app.OptimisticMagic8BallApplication;

import java.util.Random;

public class Main {
    public static void main(String[] args)
    {
        Application app = new Magic8BallApplication();
        app.run();
    }
}
