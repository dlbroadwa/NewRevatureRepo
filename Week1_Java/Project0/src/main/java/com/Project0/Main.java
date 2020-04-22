//***************************************************************************//
//Jean Daniel Aldoph II
//hyper basic POJO StockMarket simulator
//Inefficient, but functionaly
//java 1.8
//danielaldoph@yahoo.com
//***************************************************************************//
package com.Project0;

//***************************************************************************//
import com.Project0.App.*;
import static javafx.application.Platform.exit;
//***************************************************************************//
//***************************************************************************//
public class Main
{
    public static void main(String[] args)
    {
        Application app = new StockMarketApp();
        app.run();
        exit();
    }
}
//***************************************************************************//