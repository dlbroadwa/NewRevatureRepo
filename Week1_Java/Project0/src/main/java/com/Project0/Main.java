package com.Project0;

import com.Project0.App.*;
import javafx.stage.Stage;

import static javafx.application.Platform.exit;


public class Main
{
    public static void main(String[] args)
    {
        Application app = new StockMarketApp();
        app.run();
        exit();
    }
}
