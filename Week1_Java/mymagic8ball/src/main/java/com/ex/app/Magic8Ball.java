package com.ex.app;

import com.ex.shaker.Shaker;

import java.util.Random;

public class Magic8Ball implements Shaker {
    private int randMin;
    private int randMax;

    public Magic8Ball() {}
    public Magic8Ball(int randMin, int randMax)
    {
        this.randMin = randMin;
        this.randMax = randMax;
    }

    @Override
    public int shake() {
        Random r = new Random();
        return r.nextInt(randMax - randMin + 1) + randMin;
    }

    public int getRandMin() {
        return randMin;
    }

    public void setRandMin(int randMin) {
        this.randMin = randMin;
    }
}
