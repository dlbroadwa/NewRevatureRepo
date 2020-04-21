package tests;

import com.Project0.App.StockMarketApp;
import com.Project0.market.TradesRepository;
import com.Project0.screens.MainScreen;
import org.junit.Test;

public class RoutineTest
{
    @Test
    public void loopTest()
    {
        Boolean truth = false;
        while (truth != true)
        {
            while (true)
            {
                System.out.println("here");
                break;
            }
            System.out.println("True");
            truth = true;
        }
    }

    @Test
    public void mainScreenTest()
    {
        MainScreen mainScreen = new MainScreen();
        mainScreen.doScreen(new StockMarketApp());
    }

    @Test
    public void runTimeTest()
    {
        new StockMarketApp().run();
    }
}