package test;
import com.company.validation.Validate;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class ValidationTest {

    Validate validationService = new Validate();

    // Test isWithdrawable method with valid withdraw amount
    // Expect true
    @Test
    public void isWithdrawableValidWithdraw() {

        boolean actualResult = validationService.isWithdrawable(100, 1000);
        assertTrue(actualResult);
    }

    // Test isWithdrawable method with invalid withdraw amount
    // Expect false
    @Test
    public void isWithdrawableInValidWithdraw() {

        boolean actualResult = validationService.isWithdrawable(1001, 1000);
        assertFalse(actualResult);
    }

    // Test isDepositable method with valid deposit amount
    // Expect True
    @Test
    public void isDepositableInValidAmount() {

        boolean actualResult = validationService.isDepositable(1);
        assertTrue(actualResult);
    }

    // Test isDepositable method with negative withdraw amount
    // Expect false
    @Test
    public void isDepositableNegativeAmount() {
        boolean actualResult = validationService.isDepositable(-1);
        assertFalse(actualResult);
    }
}
