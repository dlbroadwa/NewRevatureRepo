package com.Project0.App;
//***************************************************************************//
import com.Project0.screens.MainScreen;
import com.Project0.screens.Screen;
import java.util.Scanner;


//**************************************************************************//
//query tradable stocks and trade stocks.



public class StockMarketApp extends com.Project0.App.Application
{
    private Scanner scanner;
    private com.Project0.App.StockMarketApp smApp;
    private Screen screen = new MainScreen();

    public StockMarketApp()
    {
        this.scanner= new Scanner(System.in);
    }

    @Override
    public void run()
    {
        while (screen != null)
        {
            screen = screen.doScreen(this);
        }
    }

    public Scanner getScanner() {
        return scanner;
    }

    public Screen getScreen()
    {
        return screen;
    }
}
