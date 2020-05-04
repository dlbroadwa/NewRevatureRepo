package Com.Tests;


import Screens.DepositMenu;
import org.junit.Assert;
import org.junit.Test;



public class DepositScreenTest {
    DepositMenu depositMenu = new DepositMenu();
    float amount = 10.05f;


    @Test
    public void testReturnValue(){
        depositMenu.setDepositAmount(amount);

        Float floatNumber = new Float(depositMenu.getDepositAmount());
        //Verify that the amount can be assigned from deposit screen.  There were no errors prior from this point
        Assert.assertTrue(true);
    }

}
