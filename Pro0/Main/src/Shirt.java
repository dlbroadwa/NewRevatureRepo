import java.util.Scanner;

public class Shirt{
	
	private String sleveLength;
	
	Shirt(){
		
		System.out.println("Creating new shirt item");
		
		System.out.println("Select sleve length (Long,short, or none)");
		
		Scanner scan = new Scanner(System.in);
		
		sleveLength = scan.next();
		
		
	}
	
	public String getSleveLength() {
		
		return sleveLength;
		
	}
	
	@Override
	public String toString(){

		return String.format("shirt");
		
	}

}
