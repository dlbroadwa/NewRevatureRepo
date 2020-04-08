package Services;
import java.util.Scanner;

public class PaymentService {
	
	PaymentService(){}
	
	PaymentService(Scanner scan, double totalOwed){
		
		System.out.println("Your total will be: " + totalOwed);
		
		System.out.println("Please select payment method:");
		
		System.out.println("1 for Credit");
		
		System.out.println("2 for Debit");
		
		System.out.println("Please input card number:");
		
		System.out.println("Please put the experation date:");
		
		System.out.println("Please input cvc code");
		
		System.out.println("Is the billing address the same as current address?(1 = yes)(2 = no)");
		
		return;
	}

}
