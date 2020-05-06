package com.ex;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

  public static void main(String[] args) throws JsonProcessingException {
    Apple a = new Apple();
    Orange o = new Orange();
    ObjectMapper om = new ObjectMapper();

    String appleString = om.writeValueAsString(a);
    String orangeString = om.writeValueAsString(o);

    System.out.println(appleString);
    System.out.println(orangeString);

    FruitBasket basket = new FruitBasket();
    basket.fruits = Arrays.asList(a, o);

    String basketString = om.writeValueAsString(basket);
    System.out.println(basketString);

    List list = Arrays.asList(a, true, 100);
    List<Fruit> fruits = Arrays.asList(a);
    String fruitsString = om.writeValueAsString(fruits);
    System.out.println(fruitsString);

    String listString = om.writeValueAsString(list);
    System.out.println(listString);

    Post p = new Post();
    p.name = "August Duet";
    p.body = "August is the best!";

    String postString = om.writeValueAsString(p);
    System.out.println(postString);

    String userPosts = om.writeValueAsString(Arrays.asList(new Post(), new Post(), new Post()));
    System.out.println(userPosts);

    Post newPost = om.readValue(postString, Post.class);
    System.out.println(newPost);

    FruitBasket newBasket = om.readValue(basketString, FruitBasket.class);
    System.out.println(newBasket);
  }
}
