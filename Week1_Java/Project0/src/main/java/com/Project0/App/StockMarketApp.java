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
    private Screen running;

    public StockMarketApp()
    {
        this.scanner= new Scanner(System.in);
    }

    @Override
    public void run()
    {
        //Runs the current Screen object. This allows us to switch between screens rather easily, going backwards causes some internal errors,
        //So you can only travel forward.
        running = screen;
        while (running != null)
        {
            //System.out.println("Starting screen");
            running = running.doScreen(this);
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
