package tests;

import com.Project0.App.StockMarketApp;
import com.Project0.market.TradesRepository;
import com.Project0.screens.MainScreen;
import org.junit.Test;
import org.omg.CORBA.INTERNAL;

import java.util.Random;

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
    public void sanityTest()
    {
        String java = "123456";
        new Integer(java);
        System.out.println(java.getClass());
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
    @Test
    public void randomTest()
    {
        Random rando = new Random();
        Integer iters = 30;
        while ((iters--)>1)
        {
            System.out.println(rando.nextInt(50));
        }
    }
}