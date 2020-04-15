package MenuUtilities;

import java.util.Scanner;

public class ObtainUserIdScreen {

    private String customerId;

    public ObtainUserIdScreen(){
        customerId = "";
    }
    public void printScreen (){
        System.out.println("Enter your bank ID: ");
    }
    public void scanCustomerIdInput (){
        Scanner myObj = new Scanner(System.in);
        customerId = myObj.next();
    }
    public String getCustomerId() {
        return customerId;
    }
}
