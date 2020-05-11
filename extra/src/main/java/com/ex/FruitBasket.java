package com.ex;

import java.util.ArrayList;
import java.util.List;

public class FruitBasket {
  public List<Fruit> fruits;

  public FruitBasket() {
    fruits = new ArrayList<>();
  }

  @Override
  public String toString() {
    return "FruitBasket{" +
      "fruits=" + fruits +
      '}';
  }
}
