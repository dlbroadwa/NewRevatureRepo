import java.util.Scanner;

public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String lookingFor = "";

		System.out.println("Welcome to the Able Sisters POS System!");
		
		System.out.println("What type of item are you looking for?:");
		
		System.out.println("	Please type 1 for shirt");
		
		System.out.println("	Please type 2 for pants");
		
		System.out.println("	Please type 3 for hat");
		
		Scanner scan = new Scanner(System.in);
		
		lookingFor = scan.next();
		
		while(!lookingFor.equals("1") && !lookingFor.equals("2") && !lookingFor.equals("3")) {
			
			System.out.println("Not a valid input please input 1,2, or 3");
			
			lookingFor = scan.next();
		
			
		}
		
		System.out.println("Are you checking our invatory? (1 = yes, 2 = no)");
		
		String answer = scan.next();
		
		answer.toLowerCase();
		
		while(!answer.equals("1") && !answer.equals("2")) {
			
			System.out.println("Not a valid input please input 1, or 2");
			
			answer = scan.next();
			
			
		}
		
		if(answer.equals("2")) {
			
			if(lookingFor.equals("1")) {
				
				Shirt obj = new Shirt();
				
				String temp = obj.getSleveLength();
				
				if(temp.equals("none")) {
					
					obj = new TankShirt();
					
				}
						
				System.out.print("Thank you for using the system! Your new " + obj.toString() + " will be ready soon!");
			
			}
		
		}
		
		
	}

}
