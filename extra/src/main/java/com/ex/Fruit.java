package com.ex;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = JSONFruitDeserializer.class)
public abstract class Fruit {
  public String type;
  public String color;

  @Override
  public String toString() {
    return "Fruit{" +
      "type='" + type + '\'' +
      ", color='" + color + '\'' +
      '}';
  }
}
