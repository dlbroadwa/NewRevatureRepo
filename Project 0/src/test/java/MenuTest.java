import menu.HoursMenu;
import menu.Menu;
import menu.PayMenu;
import org.junit.Before;
import org.junit.Test;

public class MenuTest {
    @Before
    public void testInput(){

    }

    @Test
    public void shouldSHowPrintHeader(){
        System.out.println("Welocome to Timesheets!");
    }

    @Test
    public void shouldRunMenuFromMain(){
        Menu menu = new Menu();
        //original had code below until changed to private, removed from application0
        /*menu.printHeader();
        menu.showMenu();*/
        menu.runMenu();
        //continuously looped with current code
    }

    @Test
    public void shouldRunHoursMenu(){

    }

}
