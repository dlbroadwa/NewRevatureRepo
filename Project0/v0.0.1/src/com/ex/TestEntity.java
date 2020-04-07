package com.ex;

import java.io.Serializable;

public class TestEntity implements Serializable {
    private double testNumber;

    TestEntity(int testNumber){
        this.testNumber = testNumber;
    }
}
