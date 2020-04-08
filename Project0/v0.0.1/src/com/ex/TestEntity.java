package com.ex;

import java.io.Serializable;

public class TestEntity implements Serializable {
    private double testNumber;

    TestEntity(double testNumber){
        this.testNumber = testNumber;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "testNumber=" + testNumber +
                '}';
    }
}
