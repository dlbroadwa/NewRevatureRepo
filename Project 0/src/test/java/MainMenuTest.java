import menu.HoursMenu;
import menu.MainMenu;
import org.junit.Before;
import org.junit.Test;

public class MainMenuTest {

    @Before
    public void testInput(){

    }

    @Test
    public void shouldSHowPrintHeader(){
        System.out.println("Welocome to Timesheets!");
    }

    @Test
    public void shouldRunMenuFromMain(){
        MainMenu mainMenu = new MainMenu();
        //original had code below until changed to private, removed from application0
        /*menu.printHeader();
        menu.showMenu();*/
        //mainMenu.runMenu();
        //continuously looped with current code
    }

    @Test
    public HoursMenu shouldRunHoursMenu(){
        return new HoursMenu();
    }

}
