package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.Magic8BallApplication;

import java.util.Scanner;
import java.util.SortedMap;

public class QuestionScreen implements Screen {
  Scanner scanner;
  Scanner scanner2;
  Magic8BallApplication magic8BallApplication;
  @Override
  public Screen doScreen(Application app) {
     scanner = ((Magic8BallApplication)app).getScanner();
     scanner2 = ((Magic8BallApplication)app).getScanner2();
    while(true) {



      System.out.println("Ask the 8 ball anything You want");
      System.out.println("If  a number Press I will Save it And Print Ramdom phrase for you");
      String input = scanner.nextLine();


// I add this  Condition to handle some number
      // Also in case there is a number from USer I will take that number to Serve for INDEX from my DAO

      if(input.matches("[0-9]")) //Working to handle numbers at this application
      {

      // here is where I set the index to parse at the Answer method
       ((Magic8BallApplication)app).setNumberFromQuestion(Integer.parseInt(input));
        inputFromUser(input);
        continue;
      }
      // End of my new Code
      else if(input.length() == 0 || input.trim().equals("")) {
        continue;
      } else if (input.equals("\\q")) {
        break;
      }else {
        ((Magic8BallApplication) app).setCurrentQuestion(input);


       return new AnswerScreen();

      }
    }
    return null;
  }

  // Just a reminder function to let USer know waht is going on


  public void inputFromUser(String inp){



   int id =0;
   while(true){
     System.out.println("Remember I Catch This number Until you press a Letter");

     System.out.println(" number: " + inp + " Is chosen  \n Therefor You will have Message at number: " + (Integer.parseInt(inp)+1));

     id++;

     if(id>=1){
       break;
     }
     else {

     }

   }




  }
}
